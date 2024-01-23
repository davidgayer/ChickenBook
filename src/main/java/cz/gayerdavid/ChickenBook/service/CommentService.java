package cz.gayerdavid.ChickenBook.service;

import java.util.List;

import cz.gayerdavid.ChickenBook.model.Comment;

public interface CommentService {

    Comment getComment(Long postId, Long commentId);

    List<Comment> getAllPostComments(Long postId);

    Comment saveComment(Comment comment, Long postId, Long userId);

    void deleteComment(Long postId, Long commentId);

}
