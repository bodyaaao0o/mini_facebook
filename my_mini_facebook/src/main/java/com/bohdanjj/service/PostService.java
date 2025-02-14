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

        Post newPost = new Post(++postCounter, author, content);
        posts.put(newPost.getId(), newPost);
        author.getPosts().add(newPost);
        System.out.println(author.getName() + "created new post " + content);
        return newPost;

    }

    public Post getPostByID(int id) {
        return posts.get(id);
    }

    
}
