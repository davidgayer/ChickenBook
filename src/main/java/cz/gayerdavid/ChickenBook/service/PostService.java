package cz.gayerdavid.ChickenBook.service;

import java.util.List;

import cz.gayerdavid.ChickenBook.model.Post;

public interface PostService {

    Post getPost(Long postId);

    List<Post> getAllPosts();

    List<Post> getAllUserPosts(Long userId);

    Post savePost(Post post, Long userId);

    void deletePost(Long postId);

}
