package africa.semicolon.blogproject.controller;

import africa.semicolon.blogproject.dtos.*;
import africa.semicolon.blogproject.responses.ApiResponse;
import africa.semicolon.blogproject.service.PostService;
import africa.semicolon.blogproject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        try {
            var result = userService.register(registerRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/postBlog")
    public ResponseEntity<?> Blog(@RequestBody PostRequest postRequest){
        try {
            var result = postService.postBlog(postRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequest loginRequest){
        try {
            var result = userService.login(loginRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/logOut")
    public ResponseEntity<?> userLogOut(@RequestBody LogOutRequest logOutRequest) {
        try {
            var result = userService.logOut(logOutRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/deleteByUsername")
    public ResponseEntity<?> deleteUser(@RequestBody DeleteRequest deleteRequest) {
        try {
            var result = userService.deleteByUsername(deleteRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
}
