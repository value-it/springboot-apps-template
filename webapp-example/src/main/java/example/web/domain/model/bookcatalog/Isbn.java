package example.web.domain.model.bookcatalog;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record Isbn(
        @Size(min = 13, max = 13, message = "ISBNは13桁で入力してください")
        @NotEmpty(message = "ISBNを入力してください")
        @Pattern(regexp = "[0-9]+", message = "ISBNは数値で入力してください")
        String value
) {

}
