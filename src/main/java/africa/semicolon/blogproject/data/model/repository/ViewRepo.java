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

import java.util.List;

@Repository
public interface ViewRepo extends MongoRepository<View, String> {

    View findByUsername(String username);

    List<View> findViewByPostId(String postId);
}
