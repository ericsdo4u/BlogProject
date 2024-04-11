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

import static africa.semicolon.blogproject.utilities.MapperClass.*;
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;


    @Override
    public long getListOfRegisterUsers() {
        return userRepository.count();
    }
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        validateUser(registerRequest.getUsername());
        User user = mapReg(registerRequest);
        User newUser = userRepository.save(user);
        return mapRegResponse(newUser);
    }
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User foundUser = mapLoginResponse(loginRequest);
        User userFound = userRepository.findByUsername(foundUser.getUsername().toLowerCase());
        if (isPasswordIncorrect(userFound, loginRequest.getPassword())) {
            throw new IncorrectPasswordException("username or password incorrect");
        }
        userFound.setLocked(false);
        userRepository.save(userFound);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Successfully login");
        return loginResponse;
    }
    @Override
    public LogOutResponse logOut(LogOutRequest logOutRequest){
        User setFoundUser = mapLogOutResponse(logOutRequest);
        User foundUser =  userRepository.findByUsername(setFoundUser.getUsername());
        foundUser.setLocked(true);
        userRepository.save(foundUser);
        LogOutResponse logOutResponse = new LogOutResponse();
        logOutResponse.setMessage("logout is successful");
        return logOutResponse;
    }
    @Override
    public DeleteReturnResponse deleteByUsername(DeleteRequest deleteRequest) {
       User foundUser =  userRepository.findByUsername(deleteRequest.getUsername());
        if (foundUser == null){
            throw new UserNotFoundException("user not found");
        }
                userRepository.delete(foundUser);
        DeleteReturnResponse response = new DeleteReturnResponse();
        response.setMessage("User has been successfully deleted");
        return response;
    }
    public void validateUser(String username){
        var user = userRepository.findById(username);
        if (user.isPresent()){
            throw new UserAlreadyExistException("user already exist, please login");
        }
    }
}
