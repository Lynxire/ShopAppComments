package terabu.shopappcomments.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import terabu.shopappcomments.dto.CommentRequest;
import terabu.shopappcomments.dto.CommentResponse;
import terabu.shopappcomments.service.CommentService;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("comments")
public class CommentsController {
    private final CommentService commentService;

    @PostMapping("/add")
    public CommentResponse addComment(@RequestBody @Valid CommentRequest commentRequest) {
        return commentService.addComment(commentRequest);
    }


    @PostMapping("/delete")
    public void deleteComment(@RequestParam @Min(1) @NotNull Long commentId) {
        commentService.deleteComment(commentId);
    }

    @GetMapping("/myComments")
    public List<CommentResponse> getMyComments(@RequestParam @Min(1) @NotNull Long userId) {
        return commentService.getCommentByUser(userId);
    }

    @GetMapping("/allComments")
    public List<CommentResponse> getAllComments() {
        return commentService.getAllComments();
    }

}
