package africa.semicolon.blogproject.service;

import africa.semicolon.blogproject.data.model.model.Post;
import africa.semicolon.blogproject.data.model.model.User;
import africa.semicolon.blogproject.data.model.model.View;
import africa.semicolon.blogproject.data.model.repository.PostRepository;
import africa.semicolon.blogproject.data.model.repository.UserRepository;
import africa.semicolon.blogproject.data.model.repository.ViewRepo;
import africa.semicolon.blogproject.dtos.DeleteRequest;
import africa.semicolon.blogproject.dtos.PostRequest;
import africa.semicolon.blogproject.exceptions.AuthorNotFoundException;
import africa.semicolon.blogproject.exceptions.PostNotFoundException;
import africa.semicolon.blogproject.exceptions.UserNotFoundException;
import africa.semicolon.blogproject.responses.DeleteReturnResponse;
import africa.semicolon.blogproject.responses.PostReturnResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

import static africa.semicolon.blogproject.utilities.MapperClass.*;
@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService{
    public PostRepository postRepository;
    private UserRepository userRepository;
    private ViewRepo viewRepo;


    @Override
    public PostReturnResponse postBlog(PostRequest postRequest) {
        Post post = mapPostResponse(postRequest);
        Post newPost = postRepository.save(post);
        return mapPostReturnMessage(newPost);
    }

    @Override
    public PostReturnResponse editPost(PostRequest postRequest) {
        Optional<Post> foundPost = Optional.ofNullable(postRepository.findPostByAuthor(postRequest.getAuthor()));
        if (foundPost.isEmpty()) {
            throw new AuthorNotFoundException("no author found");
        }
        Post existingPost = foundPost.get();
        existingPost.setTitle(postRequest.getTitle());
        existingPost.setContent(postRequest.getContent());
        Post editedPost = postRepository.save(existingPost);
        return mapPostReturnMessage(editedPost);
    }
    @Override
    public DeleteReturnResponse deletePost(DeleteRequest request){
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
    public long getListOfPost() {
        return postRepository.count();
    }

}
