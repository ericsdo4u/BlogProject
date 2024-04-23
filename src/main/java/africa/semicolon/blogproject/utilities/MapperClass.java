package africa.semicolon.blogproject.utilities;

import africa.semicolon.blogproject.data.model.model.Comment;
import africa.semicolon.blogproject.data.model.model.Post;
import africa.semicolon.blogproject.data.model.model.User;
import africa.semicolon.blogproject.data.model.model.View;
import africa.semicolon.blogproject.dtos.*;
import africa.semicolon.blogproject.responses.*;
import africa.semicolon.blogproject.dtos.CommentRequest;
import africa.semicolon.blogproject.responses.CommentResponse;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class MapperClass {


    public static User mapReg(RegisterRequest registerRequest){
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());

        return user;
    }
    public static RegisterResponse mapRegResponse(User user){
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setEmail(user.getEmail());
        registerResponse.setUsername(user.getUsername());
        registerResponse.setId(user.getId());
        registerResponse.setCreatedAt(DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' a").format(user.getCreatedAt()));
        return registerResponse;
    }

    public static void checkAccountState(User user){
        if (user.isLocked())
            throw new IllegalArgumentException("login");
    }
    public static Post mapPostResponse(PostRequest postRequest){
    Post post = new Post();
    post.setUsername(post.getUsername());
    post.setTitle(postRequest.getTitle());
    post.setContent(postRequest.getContent());
    post.setAuthor(postRequest.getAuthor());
    return post;
    }
    public static PostReturnResponse mapPostReturnMessage(Post post){
        PostReturnResponse postReturnResponse = new PostReturnResponse();
        postReturnResponse.setTitle(post.getTitle());
        postReturnResponse.setContent(post.getContent());
        postReturnResponse.setAuthor(post.getAuthor());
        postReturnResponse.setId(post.getId());
        postReturnResponse.setPostedAt(DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' HH:mm:ss a").format(post.getCreatedAt()));
        return postReturnResponse;
    }
    public static boolean isPasswordIncorrect(User foundUser, String password) {
        return !foundUser.getPassword().equals(password);
    }
    public static View mapView(ViewRequest request) {
        View view = new View();
        view.setUsername(request.getUsername());
        return view;
    }
    public static ViewResponse mapViewResponse(View view){
        ViewResponse viewer = new ViewResponse();
        viewer.setUsername(view.getUsername());
        viewer.setMessage("viewed your post");
        //viewer.setViewer(viewer.getViewer());
        viewer.setTimeOfView(DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' HH:mm:ss a").format(view.getTimeOfView()));
        return viewer;
    }
    public static Comment mapComment(CommentRequest request){
        Comment comment = new Comment();
        comment.setCommentMessage(request.getCommentMessage());
        comment.setUsername(request.getUsername());
        return comment;
    }
    public static CommentResponse mapCommentResponse(Comment comment) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setCommentMessage(comment.getCommentMessage());
        commentResponse.setUsername(comment.getUsername());
        //commentResponse.setId(commentResponse.getId());
        commentResponse.setPostId(comment.getPostId());
        commentResponse.setTimeCommented(DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' HH:mm:ss a").format(comment.getCommentedAt()));

        return commentResponse;
    }
}

