package cz.gayerdavid.ChickenBook.service;

import java.util.List;

import cz.gayerdavid.ChickenBook.model.User;

public interface UserService {
    User getUser(Long id);
    User saveUser(User user);
    void deleteUser(Long id);
    List<User> getAllUsers();
}
