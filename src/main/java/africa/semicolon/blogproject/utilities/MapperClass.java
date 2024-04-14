package africa.semicolon.blogproject.utilities;

import africa.semicolon.blogproject.data.model.model.Post;
import africa.semicolon.blogproject.data.model.model.User;
import africa.semicolon.blogproject.data.model.model.View;
import africa.semicolon.blogproject.dtos.*;
import africa.semicolon.blogproject.responses.*;

import java.time.format.DateTimeFormatter;

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
    public static User mapLoginResponse(LoginRequest loginRequest){
        User user = new User();
        user.setUsername(loginRequest.getUsername());
        user.setPassword(loginRequest.getPassword());
        return user;
    }

    public static User mapLogOutResponse(LogOutRequest logOutRequest){
        User user = new User();
        user.setUsername(logOutRequest.getUsername());
        user.setPassword(logOutRequest.getPassword());
        return user;
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
        postReturnResponse.setId(post.getId());
        postReturnResponse.setAuthor(post.getAuthor());
        postReturnResponse.setPostedAt(DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' HH:mm:ss a").format(post.getCreatedAt()));
        return postReturnResponse;
    }
    public static boolean isPasswordIncorrect(User foundUser, String password) {
        return !foundUser.getPassword().equals(password);
    }
    public static ViewResponse mapViewResponse(Post view){
        ViewResponse viewer = new ViewResponse();
        viewer.setMessage(view.getUsername() + "viewed your post");
        viewer.setTimeOfView(DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' HH:mm:ss a").format(view.getCreatedAt()));
        return viewer;
    }
}

