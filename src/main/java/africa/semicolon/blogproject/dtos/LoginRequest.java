package africa.semicolon.blogproject.dtos;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class LoginRequest {
    private String username;
    private String password;
}
