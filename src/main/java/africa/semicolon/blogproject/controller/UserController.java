package africa.semicolon.blogproject.controller;

import africa.semicolon.blogproject.dtos.*;
import africa.semicolon.blogproject.exceptions.BlogExceptions;
import africa.semicolon.blogproject.responses.ApiResponse;
import africa.semicolon.blogproject.service.CommentService;
import africa.semicolon.blogproject.service.PostService;
import africa.semicolon.blogproject.service.UserService;
import africa.semicolon.blogproject.service.ViewService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
@AllArgsConstructor
@RestController
public class UserController {
    private UserService userService;
    private PostService postService;
    private CommentService commentService;
    private ViewService viewService;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        try {
            var result = userService.register(registerRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (BlogExceptions e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/postBlog")
    public ResponseEntity<?> Blog(@RequestBody PostRequest postRequest){
        try {
            var result = postService.postBlog(postRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (BlogExceptions e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequest loginRequest){
        try {
            var result = userService.login(loginRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (BlogExceptions e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/logOut")
    public ResponseEntity<?> userLogOut(@RequestBody LogOutRequest logOutRequest) {
        try {
            var result = userService.logOut(logOutRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (BlogExceptions e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/editPost")
    public ResponseEntity<?> edit(@RequestBody PostRequest postRequest){
        try {
            var result = postService.editPost(postRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (BlogExceptions e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/deleteByUsername")
    public ResponseEntity<?> deleteUser(@RequestBody DeleteRequest deleteRequest) {
        try {
            var result = userService.deleteByUsername(deleteRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (BlogExceptions e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/comment")
    public ResponseEntity<?> commentOnPost(@RequestBody CommentRequest commentRequest) {
        try {
            var result = commentService.comment(commentRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (BlogExceptions e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/viewPost")
    public ResponseEntity<?> viewed(@RequestBody ViewRequest viewRequest) {
        try {
            var result = viewService.viewPost(viewRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (BlogExceptions e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
}
