package com.bohdanjj.service;
import com.bohdanjj.model.Comment;
import com.bohdanjj.model.Post;
import com.bohdanjj.model.User;

import java.util.*;

public class CommentService {

    public void addComment(String content, User commenter, Post post) {
        if (post == null || commenter == null || content == null || content.isEmpty()) {
            System.out.println("Invalid comment: Post, commenter, or content is missing.");
            return;
        }

        Comment newComment = new Comment(content, commenter);
        post.addComment(newComment);
        System.out.println(commenter.getName() + "commented on post with ID: " + post.getId() + ": " + content);
    }

    public List<Comment> getCommentsByPost(Post post) {
        if (post == null) {
            System.out.println("Post is null. Cannot retrieve comments.");
            return new ArrayList<>();
        }

        return post.getComments();
    }

    public List<Comment> getCommentsByUser(User user, List<Post> posts) {
        if (user == null || posts == null) {
            System.out.println("User or posts list is null.");
            return new ArrayList<>();
        }

        List<Comment> userComments = new ArrayList<>();
        for (Post post : posts) {
            for (Comment comment : post.getComments()) {
                if (comment.getAuthor().equals(user)) {
                    userComments.add(comment);
                }
            }
        }
        return userComments;
    }

    public void updateComment(Post post, int commentId, String newText) {
        if (post == null || newText == null || newText.isEmpty()) {
            System.out.println("Invalid input for updating comment.");
            return;
        }

        for (Comment comment : post.getComments()) {
            if (comment.getId() == commentId) {
                comment.setText(newText);
                System.out.println("Comment with ID " + commentId + " was updated.");
                return;
            }
        }
        System.out.println("Comment with ID " + commentId + " not found in post with ID " + post.getId());
    }

    public void deleteComment(Post post, int commentId) {
        if (post == null) {
            System.out.println("Post is null. Cannot delete comment.");
            return;
        }

        List<Comment> comments = post.getComments();
        boolean removed = comments.removeIf(comment -> comment.getId() == commentId);

        if (removed) {
            System.out.println("Comment with ID " + commentId + " was deleted from post with ID " + post.getId());
        }
        else {
            System.out.println("Comment with ID " + commentId + " not found in post with ID " + post.getId());
        }
    }

}
