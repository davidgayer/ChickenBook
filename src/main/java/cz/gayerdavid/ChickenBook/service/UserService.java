package cz.gayerdavid.ChickenBook.service;

import java.util.List;

import cz.gayerdavid.ChickenBook.model.User;

public interface UserService {
    User getUser(Long userId);
    User registerUser(User user);
    void deleteUser(Long userId);
    List<User> getAllUsers();
}
