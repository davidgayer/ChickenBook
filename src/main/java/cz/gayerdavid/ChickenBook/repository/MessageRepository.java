package cz.gayerdavid.ChickenBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.gayerdavid.ChickenBook.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
    
}
