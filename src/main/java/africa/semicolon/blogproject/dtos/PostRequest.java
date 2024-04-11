package africa.semicolon.blogproject.dtos;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class PostRequest {
    private String title;
    private String content;
    private String id;
}
