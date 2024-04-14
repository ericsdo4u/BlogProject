package africa.semicolon.blogproject.data.model.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@Document
public class View {
    private int views;
    private User viewer;
    private String username;
    @Id
    private String id;
    private LocalDateTime timeOfView = LocalDateTime.now();
}
