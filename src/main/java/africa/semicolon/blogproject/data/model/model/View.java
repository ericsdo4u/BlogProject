package africa.semicolon.blogproject.data.model.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@Document
public class View {
    private String username;
    private String postId;
    @Id
    private String id;
    private LocalDateTime timeOfView = LocalDateTime.now();
}
