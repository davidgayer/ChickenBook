package cz.gayerdavid.ChickenBook.service;

import java.util.List;

import cz.gayerdavid.ChickenBook.model.Like;

public interface LikeService {

    Like getLike(Long likeId);

    List<Like> getAllPostLikes(Long postId);

    Like saveLike(Like like, Long postId, Long userId); 
    
    void deleteLike(Long likeId);
    
}
