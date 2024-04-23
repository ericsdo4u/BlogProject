package africa.semicolon.blogproject.service;

import africa.semicolon.blogproject.data.model.model.Post;
import africa.semicolon.blogproject.data.model.model.User;
import africa.semicolon.blogproject.data.model.repository.PostRepository;
import africa.semicolon.blogproject.data.model.repository.UserRepository;
import africa.semicolon.blogproject.dtos.DeleteRequest;
import africa.semicolon.blogproject.dtos.LoginRequest;
import africa.semicolon.blogproject.dtos.PostRequest;
import africa.semicolon.blogproject.dtos.RegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PostServiceImplTest {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostRepository postRepository;
    @BeforeEach
    public void setup(){
        postRepository.deleteAll();

    }
    @Test
    public void testThatUserCanInitiate_A_Post(){
        User user = new User();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("dayo@gmail.com");
        registerRequest.setUsername("dayo");
        registerRequest.setPassword("1111");
        userService.register(registerRequest);
        assertEquals(1, userService.getListOfRegisterUsers());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("dayo");
        loginRequest.setPassword("1111");
        userService.login(loginRequest);
        assertFalse(user.isLocked());


        PostRequest postRequest = new PostRequest();
        postRequest.setUsername("dayo");
        postRequest.setTitle("weather");
        postRequest.setContent("is a rainy day");
        postService.postBlog(postRequest);
        assertEquals(1, postService.getListOfPost());
    }
    @Test
    public void testThatUserCan_Create_Two_Post(){

        User user = new User();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("ddon@gmail.com");
        registerRequest.setUsername("ddon");
        registerRequest.setPassword("1111");
        userService.register(registerRequest);
        assertEquals(1, userService.getListOfRegisterUsers());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("ddon");
        loginRequest.setPassword("1111");
        userService.login(loginRequest);
        assertFalse(user.isLocked());

        PostRequest postRequest = new PostRequest();
        postRequest.setUsername("ddon");
        postRequest.setTitle("weather");
        postRequest.setContent("is a rainy day");
        postService.postBlog(postRequest);

        PostRequest postRequest1 = new PostRequest();
        postRequest1.setUsername("ddon");
        postRequest1.setTitle("cold");
        postRequest1.setContent("the weather is cold");
        postService.postBlog(postRequest1);
        assertEquals(2, postService.getListOfPost());
    }
    @Test
    public void testThatPost_CanBeEdited(){

        User user = new User();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("eric@gmail.com");
        registerRequest.setUsername("eric");
        registerRequest.setPassword("1111");
        userService.register(registerRequest);
        assertEquals(1, userService.getListOfRegisterUsers());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("eric");
        loginRequest.setPassword("1111");
        userService.login(loginRequest);
        assertFalse(user.isLocked());

        PostRequest postRequest = new PostRequest();
        postRequest.setUsername("eric");
        postRequest.setTitle("weather");
        postRequest.setContent("is a rainy day");
        postRequest.setAuthor("donald");
        postService.postBlog(postRequest);

        PostRequest postRequest1 = new PostRequest();
        postRequest1.setUsername("eric");
        postRequest1.setTitle("the sky is clear");
        postRequest1.setContent("it's a sunny day");
        postRequest1.setAuthor("donald");
        postService.postBlog(postRequest1);

        PostRequest edit = new PostRequest();
        edit.setUsername("eric");
        edit.setTitle("the sky is clear");
        edit.setContent("look through");
        edit.setAuthor("donald");
        postService.editPost(edit);
        assertEquals("the sky is clear",postRequest1.getTitle());

    }
    @Test
    public void createTwoPost_UserCanDelete_One_PostTest(){

        User user = new User();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("eric0@gmail.com");
        registerRequest.setUsername("erico");
        registerRequest.setPassword("1111");
        userService.register(registerRequest);
        assertEquals(1, userService.getListOfRegisterUsers());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("erico");
        loginRequest.setPassword("1111");
        userService.login(loginRequest);
        assertFalse(user.isLocked());


        PostRequest postRequest = new PostRequest();
        postRequest.setUsername("erico");
        postRequest.setTitle("weather");
        postRequest.setContent("is a rainy day");
        postRequest.setAuthor("ddon");
        postService.postBlog(postRequest);


        PostRequest postRequest1 = new PostRequest();
        postRequest1.setUsername("erico");
        postRequest1.setTitle("cold");
        postRequest1.setContent("the weather is cold");
        postRequest1.setAuthor("eric");
        postService.postBlog(postRequest1);
        assertEquals(2, postService.getListOfPost());

        DeleteRequest request = new DeleteRequest();
        request.setUsername("erico");
        request.setAuthor("eric");
        postService.deletePost(request);
        assertEquals(1, postRepository.count());
    }
    @Test
    public void findAllPost_ByUsername(){

        User user = new User();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("ericc0@gmail.com");
        registerRequest.setUsername("ericco");
        registerRequest.setPassword("1111");
        userService.register(registerRequest);
        assertEquals(1, userService.getListOfRegisterUsers());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("ericco");
        loginRequest.setPassword("1111");
        userService.login(loginRequest);
        assertFalse(user.isLocked());


        PostRequest postRequest = new PostRequest();
        postRequest.setUsername("ericco");
        postRequest.setTitle("weather");
        postRequest.setContent("is a rainy day");
        postRequest.setAuthor("ddonn");
        postService.postBlog(postRequest);


        PostRequest postRequest1 = new PostRequest();
        postRequest1.setUsername("ericco");
        postRequest1.setTitle("cold");
        postRequest1.setContent("the weather is cold");
        postRequest1.setAuthor("ericc");
        postService.postBlog(postRequest1);

        assertEquals(2, postService.getListOfPost());
        postService.findAllPostBy("ericco");


    }
}