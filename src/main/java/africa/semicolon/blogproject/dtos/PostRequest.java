package africa.semicolon.blogproject.dtos;

import africa.semicolon.blogproject.data.model.model.User;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class PostRequest {
    private String username;
    private String title;
    private String content;
    private String author;
    private String id;
}
