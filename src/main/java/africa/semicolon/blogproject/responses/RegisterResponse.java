package africa.semicolon.blogproject.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RegisterResponse {
    private String username;
    private String email;
    private String id;
    private String createdAt;

}
