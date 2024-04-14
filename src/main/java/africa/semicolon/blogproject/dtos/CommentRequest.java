package africa.semicolon.blogproject.dtos;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class CommentRequest {
    private String commentMessage;
    private String username;
    private String id;
    private String postId;
    private String commentedAt;
}
