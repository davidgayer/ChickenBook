package cz.gayerdavid.ChickenBook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.gayerdavid.ChickenBook.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostId(Long postId);
    
}
