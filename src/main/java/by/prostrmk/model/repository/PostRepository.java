package by.prostrmk.model.repository;

import by.prostrmk.model.entity.Post;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findPostsByUsername(String username);
    Post findPostById(Long id);

//    @Query("SELECT * FROM post")

    @Transactional
    @Modifying
    @Query("update Post p set p.likes = :likes where p.id = :id")
    int setLikesToPost(@Param("id") Long id,@Param("likes") Long likes);

    @Transactional
    @Query(value = "SELECT * FROM ", nativeQuery = true)
    List<Post> findPostsBySubscriotion(Long userId);


}
