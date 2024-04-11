package africa.semicolon.blogproject.service;

import africa.semicolon.blogproject.data.model.model.User;
import africa.semicolon.blogproject.data.model.repository.UserRepository;
import africa.semicolon.blogproject.data.model.repository.PostRepository;
import africa.semicolon.blogproject.dtos.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void registerOneUser_OneUserRgisteredTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("ericsonericdon66@gmail.com");
        registerRequest.setUsername("ericson");
        registerRequest.setPassword("1234");
        userService.register(registerRequest);
        assertEquals(1, userService.getListOfRegisterUsers());
    }

    @Test
    public void registerTwoUser_TwoUserRgisteredTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("ericsonericdonn66@gmail.com");
        registerRequest.setUsername("ericsonn");
        registerRequest.setPassword("1234");
        userService.register(registerRequest);

        RegisterRequest registerRequest2 = new RegisterRequest();
        registerRequest2.setEmail("ericsonericdon661@gmail.com");
        registerRequest2.setUsername("ericson1");
        registerRequest2.setPassword("1234");
        userService.register(registerRequest);
        assertEquals(2, userService.getListOfRegisterUsers());
    }
    @Test
    public void registerOneUserAndLogInUser_UserLoginTestTest(){
        User user = new User();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("ericsonericdon66@gmail.com");
        registerRequest.setUsername("ericson");
        registerRequest.setPassword("1234");
        userService.register(registerRequest);
        assertEquals(1, userService.getListOfRegisterUsers());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("ericson");
        loginRequest.setPassword("1234");
        userService.login(loginRequest);
        assertFalse(user.isLocked());
    }
    @Test
    public void registerOneUserLoginAndLogOut_UserLogOutTest(){
        User user = new User();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("ericsonericdon66@gmail.com");
        registerRequest.setUsername("ericson2");
        registerRequest.setPassword("1234");
        userService.register(registerRequest);
        assertEquals(1, userService.getListOfRegisterUsers());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("ericson2");
        loginRequest.setPassword("1234");
        userService.login(loginRequest);
        assertFalse(user.isLocked());

        LogOutRequest logOutRequest = new LogOutRequest();
        logOutRequest.setUsername("ericson2");
        logOutRequest.setPassword("1234");
        userService.logOut(logOutRequest);
        assertFalse(user.isLocked());
    }
    @Test
    public void registerOneUserLoginAndPostABlog_BlogPostTest(){
        User user = new User();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("ericsonericdon66@gmail.com");
        registerRequest.setUsername("ericson2");
        registerRequest.setPassword("1234");
        userService.register(registerRequest);
        assertEquals(1, userService.getListOfRegisterUsers());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("ericson2");
        loginRequest.setPassword("1234");
        userService.login(loginRequest);
        assertFalse(user.isLocked());

    }
    @Test
    public void testThatUser_CanRegisterTest(){
      RegisterRequest request = new RegisterRequest();
      request.setUsername("eric");
      request.setPassword("password");
      request.setEmail("ericson@gmail.com");
        userService.register(request);
        assertEquals(1, userRepository.count());

        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.setUsername("eric");
        userService.deleteByUsername(deleteRequest);
        assertEquals(0, userRepository.count());

    }
}