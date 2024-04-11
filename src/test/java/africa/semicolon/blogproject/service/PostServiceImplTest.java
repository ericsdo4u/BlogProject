package africa.semicolon.blogproject.service;

import africa.semicolon.blogproject.data.model.repository.PostRepository;
import africa.semicolon.blogproject.dtos.PostRequest;
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
    private PostRepository postRepository;
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
//    @Test
//    public void testThatPost_CanBeEdited(){
//        PostRequest postRequest = new PostRequest();
//        postRequest.setTitle("weather");
//        postRequest.setContent("is a rainy day");
//        postService.postBlog(postRequest);
//        PostRequest postRequest1 = new PostRequest();
//        postRequest1.setTitle("weather");
//        postRequest1.setContent("is a rainy day");
//        postService.editPost(postRequest1);
//
//
//    }



}