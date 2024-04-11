package africa.semicolon.blogproject.exceptions;

public class PostCannotBeEmptyException extends RuntimeException {
    public PostCannotBeEmptyException(String message) {
        super(message);

    }
}
