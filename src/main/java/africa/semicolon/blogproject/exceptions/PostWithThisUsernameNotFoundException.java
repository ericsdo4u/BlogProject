package africa.semicolon.blogproject.exceptions;

public class PostWithThisUsernameNotFoundException extends RuntimeException {
    public PostWithThisUsernameNotFoundException(String message) {
        super(message);
    }
}
