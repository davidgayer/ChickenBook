package cz.gayerdavid.ChickenBook.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cz.gayerdavid.ChickenBook.model.User;
import cz.gayerdavid.ChickenBook.repository.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public User getUser(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User saveUser(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        // TODO Auto-generated method stub
        
    }
    
}
