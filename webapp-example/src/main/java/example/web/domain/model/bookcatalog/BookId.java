package example.web.domain.model.bookcatalog;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record BookId(
        @Min(value = 1, message = "IDが不正です")
        @NotNull(message = "IDが不正です")
        Long value
) {

    public boolean notEquals(Object obj) {
        return !this.equals(obj);
    }
}
