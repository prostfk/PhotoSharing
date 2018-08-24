package by.prostrmk.model.repository;

import by.prostrmk.model.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserById(Long id);
    User findUserByUsername(String username);
    List<User> findUsersByUsername(String username);




}
