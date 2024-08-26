package example.web.domain.model.bookcatalog;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record Title(
        @Size(max = 50, message = "書籍名は50文字以内で入力してください")
        @NotEmpty(message = "書籍名を入力してください")
        String value
) {

}
