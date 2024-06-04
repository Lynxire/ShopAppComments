package terabu.shopappcomments.mapper;

import org.mapstruct.Mapper;
import terabu.shopappcomments.dto.CommentRequest;
import terabu.shopappcomments.dto.CommentResponse;
import terabu.shopappcomments.entity.Comments;


@Mapper(componentModel = "spring")
public interface CommentMapper {
    public Comments toEntity(CommentRequest commentRequest);
    public CommentResponse toResponse(Comments comments);
}
