package africa.semicolon.blogproject.service;

import africa.semicolon.blogproject.dtos.CommentRequest;
import africa.semicolon.blogproject.responses.CommentResponse;

public interface CommentService {
    CommentResponse comment(CommentRequest request);
}
