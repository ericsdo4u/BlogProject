package africa.semicolon.blogproject.service;

import africa.semicolon.blogproject.dtos.DeleteRequest;
import africa.semicolon.blogproject.dtos.PostRequest;
import africa.semicolon.blogproject.responses.DeleteReturnResponse;
import africa.semicolon.blogproject.responses.PostReturnResponse;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    PostReturnResponse postBlog(PostRequest postRequest);
    PostReturnResponse post(PostRequest postRequest);

    long getListOfPost();
    PostReturnResponse editPost(PostRequest postRequest);

    DeleteReturnResponse deletePost(DeleteRequest request);

}
