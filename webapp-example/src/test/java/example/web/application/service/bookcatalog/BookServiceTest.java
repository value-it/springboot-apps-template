package example.web.application.service.bookcatalog;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


import example.web.domain.model.bookcatalog.Book;
import example.web.domain.model.bookcatalog.BookList;
import example.web.domain.model.bookcatalog.Isbn;
import example.web.domain.model.bookcatalog.Pages;
import example.web.domain.model.bookcatalog.Title;
import example.web.presentation.controller.bookcatalog.bookeditor.BookEditForm;
import example.web.presentation.controller.bookcatalog.bookregister.BookRegisterForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookServiceTest {

  private final BookFindService bookFindService;
  private final BookRegisterService bookRegisterService;
  private final BookUpdateService bookUpdateService;

  @Autowired
  public BookServiceTest(BookFindService bookFindService,
      BookRegisterService bookRegisterService,
      BookUpdateService bookUpdateService) {
    this.bookRegisterService = bookRegisterService;
    this.bookUpdateService = bookUpdateService;
    this.bookFindService = bookFindService;
  }

  @Test
  @Sql("classpath:db/migration/phase001/001_schema/V000_001_001__bookcatalog.sql")
  @Sql("classpath:db/migration/phase001/999_data/R__001_init.sql")
  public void 書籍リスト全件取得() {
    BookList sut = bookFindService.findAll();
    String actual = sut.toString();
    String expected = "BookList[list=["
        + "Book[id=1, title=Title[value=エンジェルタロット], isbn=Isbn[value=9784866540689], pages=Pages[value=64]], "
        + "Book[id=2, title=Title[value=誰でもできる！Google for Education導入ガイド], isbn=Isbn[value=9784296070534], pages=Pages[value=344]]"
        + "]]";
    assertThat(actual, is(expected));
  }

  @Test
  @Sql("classpath:db/migration/phase001/001_schema/V000_001_001__bookcatalog.sql")
  @Sql("classpath:db/migration/phase001/999_data/R__001_init.sql")
  public void 書籍を新規登録() {
    BookRegisterForm form =
        new BookRegisterForm(new Title("ドラゴンボール1巻"), new Isbn("1234567890123"), new Pages("50"));

    Book sut = form.toDomainEntity(bookRegisterService.nextId());
    bookRegisterService.saveAsNew(sut);

    Book actual = bookFindService.findById(3L);
    Book expected =
        new Book(3L, new Title("ドラゴンボール1巻"), new Isbn("1234567890123"), new Pages("50"));
    assertThat(actual, is(expected));
  }

  @Test
  @Sql("classpath:db/migration/phase001/001_schema/V000_001_001__bookcatalog.sql")
  @Sql("classpath:db/migration/phase001/999_data/R__001_init.sql")
  public void 書籍を更新() {
    BookEditForm form =
        new BookEditForm(1L, new Title("エンジェルタロット-編集済み"), new Isbn("9784866540680"),
            new Pages("128"));

    bookUpdateService.update(form.toDomainEntity());

    Book actual = bookFindService.findById(1L);
    Book expected =
        new Book(1L, new Title("エンジェルタロット-編集済み"), new Isbn("9784866540680"), new Pages("128"));
    assertThat(actual, is(expected));
  }
}