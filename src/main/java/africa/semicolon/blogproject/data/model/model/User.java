package africa.semicolon.blogproject.data.model.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
@Document
@Data
public class User {
    private String username;
    private String password;
    private boolean locked;
    private String email;
    @Id
    private String id;
    private List<Post> posts;
    private LocalDateTime createdAt = LocalDateTime.now();
}
