package cz.gayerdavid.ChickenBook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.gayerdavid.ChickenBook.model.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findAllByPostId(Long postId);
    
}
