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

    ThreadLocal<MongoTemplate> mongoTemplate = new ThreadLocal<>();
    static void incrementView(String postId) {
        Query query = new Query(Criteria.where("postId").is(postId));
        Update update = new Update().inc("views", 1);
        mongoTemplate.get().updateFirst(query, update, Post.class);
    }
}
