package africa.semicolon.blogproject.data.model.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Document("Post-for-blog")
public class Post {
    @DBRef
    private User username;
    private String title;
    private String content;
    private String author;
    @Id
    private String id;
    private List<Comment> comments;
    private List<View> views;
    private LocalDateTime createdAt = LocalDateTime.now();
}
