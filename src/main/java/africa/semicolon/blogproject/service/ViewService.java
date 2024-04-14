package africa.semicolon.blogproject.service;

import africa.semicolon.blogproject.dtos.PostRequest;
import africa.semicolon.blogproject.dtos.ViewRequest;
import africa.semicolon.blogproject.responses.ViewResponse;
import org.springframework.stereotype.Service;

@Service
public interface ViewService {
    ViewResponse incrementPostViews(ViewRequest request);

    ViewResponse viewPost(ViewRequest request);

    //ViewResponse incrementPostViews(String username);
}
