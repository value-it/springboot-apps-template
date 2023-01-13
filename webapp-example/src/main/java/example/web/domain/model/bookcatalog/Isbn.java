package example.web.domain.model.bookcatalog;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record Isbn(
        @Size(min = 13, max = 13, message = "ISBNは13桁で入力してください")
        @NotEmpty(message = "ISBNを入力してください")
        @Pattern(regexp = "[0-9]+", message = "ISBNは数値で入力してください")
        String value
) {

}
