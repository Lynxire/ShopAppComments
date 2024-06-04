package terabu.shopappcomments.dto;

import lombok.Data;


@Data
public class UserDataDTO {
    private Long id;
    private String name;
    private String surname;
    private Long orders;
    private Long userId;
}
