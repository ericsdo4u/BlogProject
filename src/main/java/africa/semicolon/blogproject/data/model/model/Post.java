package africa.semicolon.blogproject.data.model.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Document
public class Post {
    private String title;
    private LocalDateTime createdAt = LocalDateTime.now();

    private List<Comment> comments;
    @Id
    private String id;
    private List<View> views;
    private String content;
}
