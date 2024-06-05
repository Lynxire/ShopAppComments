package terabu.shopappcomments.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CommentRequest {
    @NotBlank(message = "пустое поле комментария")
    @Length(min = 1)
    private String comments;
    @NotBlank(message = "пустое поле комментария")
    private String email;
    @NotNull
    @Min(1)
    private Long userId;
}
