package africa.semicolon.blogproject.service;

import africa.semicolon.blogproject.data.model.model.Comment;
import africa.semicolon.blogproject.data.model.model.Post;
import africa.semicolon.blogproject.data.model.model.View;
import africa.semicolon.blogproject.data.model.repository.CommentRepo;
import africa.semicolon.blogproject.data.model.repository.PostRepository;
import africa.semicolon.blogproject.dtos.CommentRequest;
import africa.semicolon.blogproject.exceptions.PostNotFoundException;
import africa.semicolon.blogproject.responses.CommentResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static africa.semicolon.blogproject.utilities.MapperClass.mapComment;
import static africa.semicolon.blogproject.utilities.MapperClass.mapCommentResponse;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepo commentRepo;
    private PostRepository postRepository;
    private UserService userService;

    @Override
    public CommentResponse comment(CommentRequest request){
        userService.checkUser(request.getUsername());
        Optional<Post> post = postRepository.findById(request.getPostId());
        if (post.isEmpty()) throw new PostNotFoundException("post not found");

        Comment comment = mapComment(request);
        commentRepo.save(comment);
        return mapCommentResponse(comment);
    }

    public long getNumberOfView(String postId){
        List<View> views = commentRepo.findViewByPostId(postId);
        return views.size();
    }
}
