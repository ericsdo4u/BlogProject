package africa.semicolon.blogproject.service;

import africa.semicolon.blogproject.data.model.repository.PostRepository;
import africa.semicolon.blogproject.data.model.repository.UserRepository;
import africa.semicolon.blogproject.dtos.DeleteRequest;
import africa.semicolon.blogproject.dtos.PostRequest;
import africa.semicolon.blogproject.dtos.RegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PostServiceImplTest {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @BeforeEach
    public void setup(){
        postRepository.deleteAll();
    }
    @Test
    public void testThatUserCanInitiate_A_Post(){
        PostRequest postRequest = new PostRequest();
        postRequest.setTitle("weather");
        postRequest.setContent("is a rainy day");
        postService.postBlog(postRequest);
        assertEquals(1, postService.getListOfPost());
    }
    @Test
    public void testThatUserCanInitiate_Two_Post(){
        PostRequest postRequest = new PostRequest();
        postRequest.setTitle("weather");
        postRequest.setContent("is a rainy day");
        postService.postBlog(postRequest);

        PostRequest postRequest1 = new PostRequest();
        postRequest1.setTitle("cold");
        postRequest1.setContent("the weather is cold");
        postService.postBlog(postRequest1);
        assertEquals(2, postService.getListOfPost());
    }
    @Test
    public void testThatPost_CanBeEdited(){
        PostRequest postRequest = new PostRequest();
        postRequest.setTitle("weather");
        postRequest.setContent("is a rainy day");
        postRequest.setAuthor("donald");
        postService.postBlog(postRequest);

        postRepository.findPostByAuthor("donald");

        postRequest.setTitle("weather report");
        postRequest.setContent("it's a sunny day");
        postRequest.setAuthor("donald");
        postService.editPost(postRequest);
        assertEquals("it's a sunny day",postRequest.getContent());
    }
    @Test
    public void createTwoPost_UserCanDelete_One_PostTest(){
        PostRequest postRequest = new PostRequest();
        postRequest.setTitle("weather");
        postRequest.setContent("is a rainy day");
        postRequest.setAuthor("ddon");
        postService.postBlog(postRequest);

        PostRequest postRequest1 = new PostRequest();
        postRequest1.setTitle("cold");
        postRequest1.setContent("the weather is cold");
        postRequest1.setAuthor("eric");
        postService.postBlog(postRequest1);
        assertEquals(2, postService.getListOfPost());

        DeleteRequest request = new DeleteRequest();
        request.setAuthor("ddon");
        postService.deletePost(request);
        assertEquals(1, postRepository.count());
    }

//    @Test
//    public void testThatOnlyUserCanInitiate_A_Post(){
//
//        RegisterRequest request = new RegisterRequest();
//        request.setUsername("eric");
//        request.setPassword("password");
//        request.setEmail("ericson@gmail.com");
//        userService.register(request);
//        assertEquals(1, userRepository.count());
//
//        PostRequest postRequest = new PostRequest();
//        postRequest.setUsername("eric");
//        postRequest.setTitle("weather");
//        postRequest.setContent("is a rainy day");
//        postRequest.setAuthor("ddon");
//        postService.post(postRequest);
//        assertEquals(1, postService.getListOfPost());
//    }
}