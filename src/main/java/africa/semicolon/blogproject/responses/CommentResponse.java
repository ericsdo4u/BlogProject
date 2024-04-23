package africa.semicolon.blogproject.responses;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CommentResponse {
    private String commentMessage;
    private String username;
    @Id
    private String id;
    private String postId;
    private String timeCommented;
}
