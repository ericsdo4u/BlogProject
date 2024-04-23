package africa.semicolon.blogproject.service;

import africa.semicolon.blogproject.data.model.model.User;
import africa.semicolon.blogproject.dtos.DeleteRequest;
import africa.semicolon.blogproject.dtos.LogOutRequest;
import africa.semicolon.blogproject.dtos.LoginRequest;
import africa.semicolon.blogproject.dtos.RegisterRequest;
import africa.semicolon.blogproject.responses.LoginResponse;
import africa.semicolon.blogproject.responses.RegisterResponse;
import africa.semicolon.blogproject.responses.LogOutResponse;
import africa.semicolon.blogproject.responses.DeleteReturnResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    RegisterResponse register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
    LogOutResponse logOut(LogOutRequest logOutRequest);
    DeleteReturnResponse deleteByUsername(DeleteRequest deleteRequest);
    long getListOfRegisterUsers();
    void checkUser(String username);
    User findByUsername(String username);
}
