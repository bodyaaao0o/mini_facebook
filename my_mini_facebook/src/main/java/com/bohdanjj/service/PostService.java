package com.bohdanjj.service;

import com.bohdanjj.model.Post;
import com.bohdanjj.model.User;
import com.bohdanjj.model.Comment;
import com.bohdanjj.model.Like;

import java.util.*;

public class PostService {
    private final Map<Integer, Post> posts = new HashMap<>();
    private int postCounter = 0;

    public Post createPost(User author, String content) {
        if (author == null || content == null || content.isEmpty()) {
            System.out.println("Invalid post: Author or content is missing.");
            return null;
        }

        Post newPost = new Post(author, content);
        posts.put(newPost.getId(), newPost);
        author.getPosts().add(newPost);
        System.out.println(author.getName() + "created new post " + content);
        return newPost;

    }

    public Post getPostByID(int id) {
        return posts.get(id);
    }

    public void addComment(int postId, User commenter, String content) {
        Post post = getPostByID(postId);
        if (post == null) {
            System.out.println("Post with ID: " + postId + " not found");
            return;
        }

        Comment comment = new Comment(content, commenter); // Використовується ваш клас Comment
        post.addComment(comment);
        System.out.println(commenter.getName() + " commented on post with id " + postId + ": " + content);
    }

    public void addLikes(int postId, User user) {
        Post post = getPostByID(postId);
        if (post == null) {
            System.out.println("Posr with id: " + postId + "not found");
            return;
        }

        Like like = new Like(user);
        post.addLikes(like);
    }

    public void listUsersPost(User author) {
        if (author == null || author.getPosts().isEmpty()) {
            System.out.println("No posts found for this user.");
            return;
        }

        System.out.println("Posts by " + author.getName() + ":");
        for (Post post : author.getPosts()) {
            System.out.println("Post ID: " + post.getId() + ", Content " + post.getContent() + ", Likes " + post.getLikeCount());
        }
    }

    public void deletePost(int postId, User author) {
        Post post = getPostByID(postId);
        if (post == null || !post.getAuthor().equals(author)) {
            System.out.println("Cannot delete post: Either post not found or you are not the author.");
            return;
        }

        posts.remove(postId);
        author.getPosts().remove(post);
        System.out.println("Post ID " + postId + "has been deleted");

    }
}
