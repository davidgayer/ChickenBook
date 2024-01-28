package cz.gayerdavid.ChickenBook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cz.gayerdavid.ChickenBook.exception.EntityNotFoundException;
import cz.gayerdavid.ChickenBook.model.User;
import cz.gayerdavid.ChickenBook.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUser(@NonNull Long id) {
        Optional<User> user = userRepository.findById(id);
        return unwrapEntity(user, id, User.class);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(@NonNull User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(@NonNull Long id) {
        userRepository.deleteById(id);
    }

    private <T> T unwrapEntity(Optional<T> entity, Long id, Class<T> entityType) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new EntityNotFoundException(id, entityType);
    }

}
