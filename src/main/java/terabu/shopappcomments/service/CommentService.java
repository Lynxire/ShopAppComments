package terabu.shopappcomments.service;

import com.fasterxml.jackson.databind.JsonSerializable;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import terabu.shopappcomments.dto.CommentRequest;
import terabu.shopappcomments.dto.CommentResponse;
import terabu.shopappcomments.dto.UserDTO;
import terabu.shopappcomments.dto.UserDataDTO;
import terabu.shopappcomments.entity.Comments;
import terabu.shopappcomments.mapper.CommentMapper;
import terabu.shopappcomments.repository.CommentsRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentsRepository commentsRepository;
    private final CommentMapper commentMapper;
    private final UserService userService;
    private final UserDataService userDataService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public CommentResponse addComment(CommentRequest commentRequest) {
        UserDTO user = userService.findUserById(commentRequest.getUserId());
        Comments comments = commentMapper.toEntity(commentRequest);
        comments.setUserId(user.getId());
        commentsRepository.save(comments);
        CommentResponse response = commentMapper.toResponse(comments);
        kafkaTemplate.send("my-topic", response);
        return response;
    }

    public void deleteComment(Long commentId) {
        commentsRepository.findById(commentId).ifPresent(commentsRepository::delete);

    }

    public List<CommentResponse> getCommentByUser(Long userId) {
        UserDataDTO dataDTO = userDataService.findDataByUserId(userId);
        List<Comments> list = commentsRepository.findAllByUserId(userId);
        List<CommentResponse> responses = new ArrayList<>();
        list.forEach(comments -> {
            CommentResponse commentResponse = commentMapper.toResponse(comments);
            commentResponse.setName(dataDTO.getName());
            responses.add(commentResponse);
        });

        return responses;
    }

    public List<CommentResponse> getAllComments() {
        List<Comments> commentsList = commentsRepository.findAll();

        return commentsList.stream().map(comments -> {
            CommentResponse commentResponse = commentMapper.toResponse(comments);
            UserDataDTO dataDTO = userDataService.findDataByUserId(comments.getUserId());
            commentResponse.setName(dataDTO.getName());
            return commentResponse;
        }).toList();
    }
}
