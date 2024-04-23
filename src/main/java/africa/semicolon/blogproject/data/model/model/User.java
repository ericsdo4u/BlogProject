package africa.semicolon.blogproject.data.model.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Document("User-for-blog")
@Data
public class User {
    private String username;
    private String password;
    private boolean locked;
    private String email;
    @Id
    private String id;
    private LocalDateTime createdAt = LocalDateTime.now();
    @DBRef
    List<Post>posts = new ArrayList<>();
}
