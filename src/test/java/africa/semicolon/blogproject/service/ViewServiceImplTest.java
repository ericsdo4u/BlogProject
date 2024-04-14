package africa.semicolon.blogproject.service;

import africa.semicolon.blogproject.data.model.repository.ViewRepo;
import africa.semicolon.blogproject.dtos.PostRequest;
import africa.semicolon.blogproject.dtos.RegisterRequest;
import africa.semicolon.blogproject.dtos.ViewRequest;
import africa.semicolon.blogproject.responses.PostReturnResponse;
import africa.semicolon.blogproject.responses.RegisterResponse;
import africa.semicolon.blogproject.responses.ViewResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ViewServiceImplTest {
    @Autowired
    private ViewService viewService;
    @Autowired
    private ViewRepo viewRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @Test
    public void testThatPostCnaBeViewed(){
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
        assertEquals("java",postReturnResponse.getTitle());

        ViewRequest viewRequest = new ViewRequest();
        viewRequest.setUsername("don");
        ViewResponse viewResponse = viewService.viewPost(viewRequest);
        assertEquals(1, viewService.getListOfViewers());
        assertEquals("don",viewResponse.getUsername());
    }
}