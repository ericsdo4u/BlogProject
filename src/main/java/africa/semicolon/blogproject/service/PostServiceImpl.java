package africa.semicolon.blogproject.service;

import africa.semicolon.blogproject.data.model.model.Post;
import africa.semicolon.blogproject.data.model.model.User;
import africa.semicolon.blogproject.data.model.repository.PostRepository;
import africa.semicolon.blogproject.data.model.repository.UserRepository;
import africa.semicolon.blogproject.dtos.DeleteRequest;
import africa.semicolon.blogproject.dtos.PostRequest;
import africa.semicolon.blogproject.exceptions.*;
import africa.semicolon.blogproject.responses.DeleteReturnResponse;
import africa.semicolon.blogproject.responses.PostReturnResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import static africa.semicolon.blogproject.utilities.MapperClass.*;
@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService{
    @Autowired
    public PostRepository postRepository;
    @Autowired
    public UserService userService;


    @Override
    public PostReturnResponse postBlog(PostRequest postRequest) {
        checkTitle(postRequest.getTitle().toLowerCase());
        User foundUser = userService.findByUsername(postRequest.getUsername().toLowerCase());
        checkAccountState(foundUser);
        Post post = mapPostResponse(postRequest);
        Post newPost = postRepository.save(post);
        return mapPostReturnMessage(newPost);
    }
    @Override
    public PostReturnResponse editPost(PostRequest postRequest) {
        User foundUser = userService.findByUsername(postRequest.getUsername().toLowerCase());
        if (!foundUser.getUsername().equals(postRequest.getUsername().toLowerCase())){throw new UserNotFoundException("user not found");}
        Optional<Post> foundPost = Optional.ofNullable(postRepository.findByTitle(postRequest.getTitle().toLowerCase()));
        if (foundPost.isEmpty()) {
            throw new TitleNotFoundException("no title found");
        }
        Post existingPost = foundPost.get();
        existingPost.setUsername(foundUser);
        existingPost.setTitle(postRequest.getTitle());
        existingPost.setContent(postRequest.getContent());
        existingPost.setAuthor(postRequest.getAuthor());
        Post editedPost = postRepository.save(existingPost);
        return mapPostReturnMessage(editedPost);
    }
    @Override
    public DeleteReturnResponse deletePost(DeleteRequest request){
        userService.checkUser(request.getUsername().toLowerCase());
        Post foundPost = postRepository.findPostByAuthor(request.getAuthor());
        if (foundPost == null){
            throw new PostNotFoundException("post not found");
        }
        postRepository.delete(foundPost);
        DeleteReturnResponse response = new DeleteReturnResponse();
        response.setMessage("post deleted");
        return response;
    }

    @Override
    public List<Post> findAllPostBy(String username) {
        List<Post> foundPost = postRepository.findPostByUsername(username.toLowerCase());
        if (foundPost.isEmpty()){
            throw new PostNotFoundException("post not found");
        }
        postRepository.saveAll(foundPost);
        return foundPost;
        //return postRepository.findAll();
    }

    @Override
    public long getListOfPost() {
        return postRepository.count();
    }
    @Override
    public void checkTitle(String title){
        Optional<Post> foundTitle = postRepository.findPostByTitle(title);
        if (foundTitle.isPresent()){
            throw new TitleAlreadyExistException("post title already exist, consider editing post");
        }
    }
}
