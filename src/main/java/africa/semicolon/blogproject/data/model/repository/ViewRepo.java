package africa.semicolon.blogproject.data.model.repository;


import africa.semicolon.blogproject.data.model.model.Post;
import africa.semicolon.blogproject.data.model.model.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewRepo extends MongoRepository<View, String> {

    View findByUsername(String username);

    @Autowired
    ThreadLocal<MongoTemplate> mongoTemplate = new ThreadLocal<>();
    public default Post incrementView(Post postId) {
        Query query = new Query(Criteria.where("postId").is(postId));
        Update update = new Update().inc("views", 1);
        mongoTemplate.get().updateFirst(query, update, Post.class);
        return postId;
    }

}
