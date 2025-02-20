package com.bohdanjj.controller;

import com.bohdanjj.model.Post;
import com.bohdanjj.model.User;
import com.bohdanjj.service.PostService;
import com.bohdanjj.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bohdanjj.dto.CreatePostRequest;
import com.bohdanjj.dto.AddCommentRequest;
import jakarta.validation.Valid;


import java.util.List;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(@RequestParam int userId) {
        User user = userService.getUserById(userId);
        List<Post> userPosts = user.getPosts();
        return ResponseEntity.ok(userPosts);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestParam int userId, @Valid @RequestBody CreatePostRequest request) {
        User author = userService.getUserById(userId);
        Post post = postService.createPost(author, request.getContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<Void> addComment(
            @PathVariable int postId,
            @RequestParam int userId,
            @Valid @RequestBody AddCommentRequest request) {
        User commenter = userService.getUserById(userId);
        postService.addComment(postId, commenter, request.getContent());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{postId}/likes")
    public ResponseEntity<Void> addLike(@PathVariable int postId, @RequestParam int userId) {
        User user = userService.getUserById(userId);
        postService.addLikes(postId, user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable int postId, @RequestParam int userId) {
        User author = userService.getUserById(userId);
        postService.deletePost(postId, author);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable int postId) {
        Post post = postService.getPostByID(postId);
        return ResponseEntity.ok(post);
    }
}
