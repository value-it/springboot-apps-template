package example.web.application.service.bookcatalog;

import example.web.domain.model.bookcatalog.*;
import example.web.presentation.controller.bookcatalog.bookeditor.BookEditForm;
import example.web.presentation.controller.bookcatalog.bookregister.BookRegisterForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("with_h2db")
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
                + "Book[bookId=BookId[value=10001], revision=BookRevision[value=1], title=Title[value=エンジェルタロット], isbn=Isbn[value=9784866540689], pages=Pages[value=64]], "
                + "Book[bookId=BookId[value=10002], revision=BookRevision[value=2], title=Title[value=誰でもできる！Google for Education導入ガイド], isbn=Isbn[value=9784296070534], pages=Pages[value=344]]"
                + "]]";
        assertThat(actual, is(expected));
    }

    @Test
    @Sql("classpath:db/migration/phase001/001_schema/V000_001_001__bookcatalog.sql")
    @Sql("classpath:db/migration/phase001/999_data/R__001_init.sql")
    public void IDを指定して書籍取得() {
        Book actual = bookFindService.findById(new BookId(10001L));
        Book expected = new Book(new BookId(10001L), new BookRevision(1L), new Title("エンジェルタロット"), new Isbn("9784866540689"), new Pages(64));
        assertThat(actual, is(expected));
    }

    @Test
    @Sql("classpath:db/migration/phase001/001_schema/V000_001_001__bookcatalog.sql")
    @Sql("classpath:db/migration/phase001/999_data/R__001_init.sql")
    public void 書籍を新規登録() {
        BookRegisterForm form =
                new BookRegisterForm("ドラゴンボール1巻", "1234567890123", 50);

        bookRegisterService.saveAsNew(form);

        Book actual = bookFindService.findById(new BookId(10003L));
        Book expected =
                new Book(new BookId(10003L), new BookRevision(3L), new Title("ドラゴンボール1巻"), new Isbn("1234567890123"), new Pages(50));
        assertThat(actual, is(expected));
    }

    @Test
    @Sql("classpath:db/migration/phase001/001_schema/V000_001_001__bookcatalog.sql")
    @Sql("classpath:db/migration/phase001/999_data/R__001_init.sql")
    public void 書籍を更新() {
        BookEditForm form =
                new BookEditForm(10001L, "エンジェルタロット-編集済み", "9784866540680", 128);

        bookUpdateService.update(form);

        Book actual = bookFindService.findById(new BookId(10001L));
        Book expected =
                new Book(new BookId(10001L), new BookRevision(3L), new Title("エンジェルタロット-編集済み"), new Isbn("9784866540680"), new Pages(128));
        assertThat(actual, is(expected));
    }
}