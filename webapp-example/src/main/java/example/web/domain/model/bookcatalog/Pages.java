package example.web.domain.model.bookcatalog;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public record Pages(
    @Min(value = 1, message = "ページ数は1以上の整数を入力してください")
    @Max(value = 10000, message = "ページ数は10000以内の整数を入力してください")
    @NotEmpty(message = "ページ数を入力してください")
    @Pattern(regexp = "[0-9]+", message = "ページ数は半角整数を入力してください")
    String value
) {

}
