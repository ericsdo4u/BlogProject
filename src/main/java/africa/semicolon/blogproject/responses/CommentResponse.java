package africa.semicolon.blogproject.responses;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CommentResponse {
    private String commentMessage;
    private String postId;
    private String username;
    private String timeCommented;
}
