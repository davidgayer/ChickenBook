package cz.gayerdavid.ChickenBook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.gayerdavid.ChickenBook.model.Post;


public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUserId(Long userId);

    
}
