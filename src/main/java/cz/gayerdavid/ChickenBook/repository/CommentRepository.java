package cz.gayerdavid.ChickenBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.gayerdavid.ChickenBook.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}
