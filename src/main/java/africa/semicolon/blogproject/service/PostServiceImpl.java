package africa.semicolon.blogproject.service;

import africa.semicolon.blogproject.data.model.model.Post;
import africa.semicolon.blogproject.data.model.model.User;
import africa.semicolon.blogproject.data.model.repository.PostRepository;
import africa.semicolon.blogproject.data.model.repository.UserRepository;
import africa.semicolon.blogproject.dtos.PostRequest;
import africa.semicolon.blogproject.responses.PostReturnResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import static africa.semicolon.blogproject.utilities.MapperClass.*;
@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService{
    private PostRepository postRepository;


    @Override
    public PostReturnResponse postBlog(PostRequest postRequest) {
        Post post = mapPostResponse(postRequest);
        Post newPost = postRepository.save(post);
        return mapPostReturnMessage(newPost);
    }
    @Override
    public long getListOfPost() {
        return postRepository.count();
    }
}
