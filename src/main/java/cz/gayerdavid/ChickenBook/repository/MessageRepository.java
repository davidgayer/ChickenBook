package cz.gayerdavid.ChickenBook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.gayerdavid.ChickenBook.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByUserId(Long userId);
    
}
