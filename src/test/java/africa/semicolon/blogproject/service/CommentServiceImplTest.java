package africa.semicolon.blogproject.service;

import africa.semicolon.blogproject.data.model.repository.CommentRepo;
import africa.semicolon.blogproject.dtos.CommentRequest;
import africa.semicolon.blogproject.dtos.PostRequest;
import africa.semicolon.blogproject.dtos.RegisterRequest;
import africa.semicolon.blogproject.responses.PostReturnResponse;
import africa.semicolon.blogproject.responses.RegisterResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceImplTest {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentRepo commentRepo;

    @Test
    public void testThatUserCanCommentOnAPost(){
        RegisterRequest request = new RegisterRequest();
        request.setEmail("ericson@gmail.com");
        request.setUsername("don");
        request.setPassword("password");
        RegisterResponse response = userService.register(request);
        assertEquals(1, userService.getListOfRegisterUsers());
        assertEquals("don", response.getUsername());

        PostRequest postRequest = new PostRequest();
        postRequest.setTitle("java");
        postRequest.setContent("programming requires thinking");
        PostReturnResponse postReturnResponse = postService.postBlog(postRequest);
        assertEquals(1, postService.getListOfPost());


        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setCommentMessage("this is a nice post");
        commentRequest.setUsername("don");
        commentRequest.setPostId(postReturnResponse.getId());
        commentService.comment(commentRequest);
        assertEquals(1, commentRepo.count());
    }


}