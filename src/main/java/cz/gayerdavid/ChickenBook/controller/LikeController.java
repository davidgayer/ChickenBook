package cz.gayerdavid.ChickenBook.controller;

import org.springframework.web.bind.annotation.RestController;

import cz.gayerdavid.ChickenBook.model.Like;
import cz.gayerdavid.ChickenBook.service.LikeService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    LikeService likeService;

    @GetMapping("/{likeId}")
    public ResponseEntity<Like> getLike(@PathVariable Long likeId) {
        return new ResponseEntity<>(likeService.getLike(likeId), HttpStatus.OK);
    }
    

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Like>> getAllPostLikes(@PathVariable Long postId) {
        return new ResponseEntity<>(likeService.getAllPostLikes(postId), HttpStatus.OK);
    }

    @PostMapping("/post/{postId}/user/{userId}")
    public ResponseEntity<Like> saveLike(@RequestBody Like like, @PathVariable Long postId, @PathVariable Long userId) {
        
        return new ResponseEntity<>(likeService.saveLike(like, postId, userId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity<HttpStatus> deleteLike(@PathVariable Long likeId) {
        likeService.deleteLike(likeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
    
}
