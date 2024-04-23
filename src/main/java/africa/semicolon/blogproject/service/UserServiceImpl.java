package africa.semicolon.blogproject.service;

import africa.semicolon.blogproject.data.model.model.User;
import africa.semicolon.blogproject.data.model.repository.UserRepository;
import africa.semicolon.blogproject.dtos.DeleteRequest;
import africa.semicolon.blogproject.dtos.LogOutRequest;
import africa.semicolon.blogproject.dtos.LoginRequest;
import africa.semicolon.blogproject.dtos.RegisterRequest;
import africa.semicolon.blogproject.exceptions.IncorrectPasswordException;
import africa.semicolon.blogproject.exceptions.UserAlreadyExistException;
import africa.semicolon.blogproject.exceptions.UserNotFoundException;
import africa.semicolon.blogproject.responses.LoginResponse;
import africa.semicolon.blogproject.responses.RegisterResponse;
import africa.semicolon.blogproject.responses.LogOutResponse;
import africa.semicolon.blogproject.responses.DeleteReturnResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static africa.semicolon.blogproject.utilities.MapperClass.*;
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;


    @Override
    public long getListOfRegisterUsers() {
        return userRepository.count();
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        if (registerRequest.getUsername().isEmpty() || registerRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Username or password cannot be empty");
        }
        validateUser(registerRequest.getUsername().toLowerCase());
        User user = mapReg(registerRequest);
        User newUser = userRepository.save(user);
        return mapRegResponse(newUser);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        checkUser(loginRequest.getUsername().toLowerCase());
        User foundUser = userRepository.findByUsername(loginRequest.getUsername().toLowerCase());
        if (isPasswordIncorrect(foundUser, loginRequest.getPassword())) {
            throw new IncorrectPasswordException("username or password incorrect");
        }
        foundUser.setLocked(false);
        userRepository.save(foundUser);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Successfully logged in");
        return loginResponse;
    }

    @Override
    public LogOutResponse logOut(LogOutRequest logOutRequest) {
        checkUser(logOutRequest.getUsername().toLowerCase());
        User foundUser = userRepository.findByUsername(logOutRequest.getUsername().toLowerCase());
        if (isPasswordIncorrect(foundUser, logOutRequest.getPassword())) {
            throw new IncorrectPasswordException("username or password incorrect");
        }
        foundUser.setLocked(true);
        userRepository.save(foundUser);
        LogOutResponse logOutResponse = new LogOutResponse();
        logOutResponse.setMessage("logout is successful");
        return logOutResponse;
    }

    @Override
    public DeleteReturnResponse deleteByUsername(DeleteRequest deleteRequest) {
        User foundUser = userRepository.findByUsername(deleteRequest.getUsername().toLowerCase());
        if (foundUser == null) {
            throw new UserNotFoundException("user not found");
        }
        checkAccountState(foundUser);
        userRepository.delete(foundUser);
        DeleteReturnResponse response = new DeleteReturnResponse();
        response.setMessage("User has been successfully deleted");
        return response;
    }

    public void validateUser(String username) {
        var user = userRepository.findById(username.toLowerCase());
        if (user.isPresent()) {
            throw new UserAlreadyExistException("user already exist, please login");
        }
    }

    @Override
    public void checkUser(String username) {
        Optional<User> user = userRepository.findUserByUsername(username.toLowerCase());
        if (user.isEmpty()) throw new UserNotFoundException("User not found");
    }
    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername((username.toLowerCase()));
        if (user == null) throw new UserNotFoundException("User not found");
        return user;
    }
}
