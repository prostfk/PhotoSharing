package by.prostrmk.model.service;

import by.prostrmk.model.entity.User;
import by.prostrmk.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("jpaUserService")
@Repository
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    EntityManager entityManager;


    @Override
    public List<User> findAll() {

        return new ArrayList<>();
    }

    @Override
    public User findByUsername(String username) {

        String hql = "SELECT WHERE username=" + username + " FROM User u";
        Query query = entityManager.createQuery(hql);
        return (User) query.getSingleResult();
    }
}
