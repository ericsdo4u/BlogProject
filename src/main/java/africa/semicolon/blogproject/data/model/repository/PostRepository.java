package africa.semicolon.blogproject.data.model.repository;

import africa.semicolon.blogproject.data.model.model.Post;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    Post findPostByAuthor(String author);
    Post findByUsername(String username);

}
