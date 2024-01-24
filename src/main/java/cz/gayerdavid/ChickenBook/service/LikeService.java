package cz.gayerdavid.ChickenBook.service;

import java.util.List;

import cz.gayerdavid.ChickenBook.model.Like;

public interface LikeService {

    List<Like> getAllPostLikes(Long postId);

    Like saveLike(Like like, Long postId, Long userId);  
    
}
