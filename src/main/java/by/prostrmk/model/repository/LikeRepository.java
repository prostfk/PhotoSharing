package by.prostrmk.model.repository;

import by.prostrmk.model.entity.Like;
import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<Like, Long> {

    Like findLikeByPostIdAndAndUserId(Long postId, Long userId);

}
