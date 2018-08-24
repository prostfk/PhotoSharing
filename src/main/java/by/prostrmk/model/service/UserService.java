package by.prostrmk.model.service;

import by.prostrmk.model.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findByUsername(String username);

}
