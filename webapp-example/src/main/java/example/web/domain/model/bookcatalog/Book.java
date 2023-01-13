package example.web.domain.model.bookcatalog;

public record Book(
        BookId bookId,
        BookRevision revision,
        Title title,
        Isbn isbn,
        Pages pages
) {

}
