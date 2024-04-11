package africa.semicolon.blogproject.data.model.repository;

import africa.semicolon.blogproject.data.model.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
