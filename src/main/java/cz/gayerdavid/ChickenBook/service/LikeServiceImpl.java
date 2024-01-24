package cz.gayerdavid.ChickenBook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cz.gayerdavid.ChickenBook.exception.PostNotFoundException;
import cz.gayerdavid.ChickenBook.exception.UserNotFoundException;
import cz.gayerdavid.ChickenBook.model.Like;
import cz.gayerdavid.ChickenBook.model.Post;
import cz.gayerdavid.ChickenBook.model.User;
import cz.gayerdavid.ChickenBook.repository.LikeRepository;
import cz.gayerdavid.ChickenBook.repository.PostRepository;
import cz.gayerdavid.ChickenBook.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public Like getLike(@NonNull Long likeId) {
        return likeRepository.findById(likeId).get();
    }

    @Override
    public List<Like> getAllPostLikes(@NonNull Long postId) {
        return likeRepository.findAllByPostId(postId);
    }

    @Override
    public Like saveLike(@NonNull Long postId, @NonNull Long userId) {
        Like like = new Like();
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            like.setPost(post.get());
        } else {
            throw new PostNotFoundException(postId);
        }
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            like.setUser(user.get());
        } else {
            throw new UserNotFoundException(userId);
        }
        System.out.println("Received Like Request: " + like);
        return likeRepository.save(like);
    }

    @Override
    public void deleteLike(@NonNull Long likeId) {
        likeRepository.deleteById(likeId);
    }

}
