package example.web.domain.model.bookcatalog;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record Pages(
        @Min(value = 1, message = "ページ数は1以上の整数を入力してください")
        @Max(value = 10000, message = "ページ数は10000以内の整数を入力してください")
        @NotNull(message = "ページ数を入力してください")
        Integer value
) {

}
