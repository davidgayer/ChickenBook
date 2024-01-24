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

    LikeRepository likeRepository;
    PostRepository postRepository;
    UserRepository userRepository;

    @Override
    public Like getLike(@NonNull Long likeId) {
        return likeRepository.findById(likeId).get();
    }

    @Override
    public List<Like> getAllPostLikes(Long postId) {
        return likeRepository.findAllByPostId(postId);
    }

    @Override
    public Like saveLike(@NonNull Like like, @NonNull Long postId, @NonNull Long userId) {
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
        return likeRepository.save(like);
    }

    @Override
    public void deleteLike(@NonNull Long likeId) {
        likeRepository.deleteById(likeId);
    }

}
