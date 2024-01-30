package cz.gayerdavid.ChickenBook.service;

import java.util.List;

import cz.gayerdavid.ChickenBook.exception.WrongPasswordFormatException;
import cz.gayerdavid.ChickenBook.model.User;

public interface UserService {
    User getUser(Long userId)
    void deleteUser(Long userId);
    List<User> getAllUsers();
}
