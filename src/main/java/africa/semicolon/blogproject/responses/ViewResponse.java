package africa.semicolon.blogproject.responses;

import africa.semicolon.blogproject.data.model.model.User;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class ViewResponse {
    private String username;
    private User viewer;
    private String timeOfView;
    private String message;
}
