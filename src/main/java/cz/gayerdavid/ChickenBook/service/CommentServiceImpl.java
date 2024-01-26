package cz.gayerdavid.ChickenBook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cz.gayerdavid.ChickenBook.exception.EntityNotFoundException;
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
        return unwrapEntity(comment, commentId, Comment.class);
    }

    @Override
    public List<Comment> getAllPostComments(@NonNull Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @Override
    public Comment saveComment(@NonNull Comment comment, @NonNull Long postId, @NonNull Long userId) {
        Optional<Post> post = postRepository.findById(postId);
        comment.setPost(unwrapEntity(post, postId, Post.class));
        Optional<User> user = userRepository.findById(userId);
        comment.setUser(unwrapEntity(user, userId, User.class));
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(@NonNull Long PostId, @NonNull Long commentId) {
        commentRepository.deleteById(commentId);
    }

    private <T> T unwrapEntity(Optional<T> entity, Long id, Class<T> entityType) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new EntityNotFoundException(id, entityType);
    }

    

}
