package terabu.shopappcomments.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import terabu.shopappcomments.entity.Comments;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    public List<Comments> findAllByUserId(Long userId);

}
