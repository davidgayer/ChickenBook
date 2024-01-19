package cz.gayerdavid.ChickenBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.gayerdavid.ChickenBook.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
