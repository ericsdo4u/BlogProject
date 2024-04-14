package africa.semicolon.blogproject.data.model.repository;

import africa.semicolon.blogproject.data.model.model.Comment;
import africa.semicolon.blogproject.data.model.model.View;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends MongoRepository<Comment, String> {
    List<View> findViewByPostId(String postId);

}
