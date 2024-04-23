package africa.semicolon.blogproject.dtos;

import africa.semicolon.blogproject.data.model.model.User;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class ViewRequest {
    private String username;
    private User viewer;
    private String postId;
    private String timeOfView;
}
