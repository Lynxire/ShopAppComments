package terabu.shopappcomments.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_id")
    @SequenceGenerator(name = "comments_id", sequenceName = "comments_seq", allocationSize = 1)
    private Long id;
    private String comments;
    private String email;
    @JoinColumn(name = "userId")
    private Long userId;

}
