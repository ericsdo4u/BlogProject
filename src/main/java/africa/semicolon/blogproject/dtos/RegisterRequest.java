package africa.semicolon.blogproject.dtos;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class RegisterRequest {
    private String email;
    private String username;
    private String password;

}
