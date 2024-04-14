package africa.semicolon.blogproject.data.model.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Document
@Data
public class Comment {
    private LocalDateTime timeOfView;
    private User viewer;
    private String username;
    @Id
    private String id;
    private LocalDateTime commentedAt = LocalDateTime.now();
}
