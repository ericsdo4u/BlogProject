package africa.semicolon.blogproject.responses;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class LogOutResponse {
    private String message;
}
