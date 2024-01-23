package cz.gayerdavid.ChickenBook.service;

import java.util.List;

import cz.gayerdavid.ChickenBook.model.Comment;
import cz.gayerdavid.ChickenBook.model.Post;

public interface CommentService {

    Comment getComment(Long postId);
    List<Comment> getAllPostComments(Long postId);
    Post saveComment(Post post, Long postId, Long userId);
    void deleteComment(Long postId);
    
}
