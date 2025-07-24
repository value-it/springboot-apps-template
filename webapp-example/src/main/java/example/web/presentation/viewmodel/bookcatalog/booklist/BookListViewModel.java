package example.web.presentation.viewmodel.bookcatalog.booklist;

import example.web.domain.model.bookcatalog.BookList;

public record BookListViewModel(BookViewModel[] books) {

    public static BookListViewModel of(BookList bookList) {
        return new BookListViewModel(
                bookList.list().stream()
                        .map(BookViewModel::of)
                        .toArray(BookViewModel[]::new)
        );
    }
}
