package exercise.repository;

import exercise.model.Comment;
import exercise.model.Post;
import liquibase.pro.packaged.I;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    // BEGIN
    Iterable<Comment> findAllByPostId(long postId);
    Optional<Comment> findByPostIdAndId(long postId, long id);
    // END
}
