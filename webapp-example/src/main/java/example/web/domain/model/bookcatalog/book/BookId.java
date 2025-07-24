package example.web.domain.model.bookcatalog.book;


public record BookId(
        Long value
) {

    public boolean notEquals(Object obj) {
        return !this.equals(obj);
    }
}
