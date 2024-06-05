package terabu.shopappcomments.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@JsonSerialize
@Data
public class CommentResponse {
    private String email;
    private String comments;
    private String name;
    private String id;
}
