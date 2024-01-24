package cz.gayerdavid.ChickenBook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cz.gayerdavid.ChickenBook.exception.CommentNotFoundException;
import cz.gayerdavid.ChickenBook.exception.PostNotFoundException;
import cz.gayerdavid.ChickenBook.exception.UserNotFoundException;
import cz.gayerdavid.ChickenBook.model.Comment;
import cz.gayerdavid.ChickenBook.model.Post;
import cz.gayerdavid.ChickenBook.model.User;
import cz.gayerdavid.ChickenBook.repository.CommentRepository;
import cz.gayerdavid.ChickenBook.repository.PostRepository;
import cz.gayerdavid.ChickenBook.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public Comment getComment(@NonNull Long postId, @NonNull Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            return comment.get();
        } else {
            throw new CommentNotFoundException(commentId);
        }
    }

    @Override
    public List<Comment> getAllPostComments(@NonNull Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @Override
    public Comment saveComment(@NonNull Comment comment, @NonNull Long postId, @NonNull Long userId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            comment.setPost(post.get());
        } else {
            throw new PostNotFoundException(postId);
        }
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            comment.setUser(user.get());
        } else {
            throw new UserNotFoundException(userId);
        }
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(@NonNull Long PostId, @NonNull Long commentId) {
        commentRepository.deleteById(commentId);
    }

}
