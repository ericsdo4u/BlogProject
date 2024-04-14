package africa.semicolon.blogproject.service;

import africa.semicolon.blogproject.dtos.ViewRequest;
import africa.semicolon.blogproject.responses.ViewResponse;
import org.springframework.stereotype.Service;

@Service
public interface ViewService {
    ViewResponse viewPost(ViewRequest request);
    long getListOfViewers();
}
