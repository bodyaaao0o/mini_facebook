package com.bohdanjj.model;
import com.bohdanjj.model.Comment;
import java.util.*;


public class Post {
    private static int postCounter = 0;
    private int id;
    private User author;
    private String content;
    private List<Comment> comments;
    private List<Like> likes;

    public Post(User author, String content) {
        this.id = ++postCounter;
        this.author = author;
        this.content = content;
        this.comments = new ArrayList<>();
        this.likes = new ArrayList<>();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void addLikes(Like like) {
        boolean alreadyLiked = likes.stream().anyMatch(l -> l.getUser().equals(like.getUser()));
        if (!alreadyLiked) {
            likes.add(like);
            System.out.println(like.getUser().getName() + "liked the post!");
        }
        else {
            System.out.println(like.getUser().getName() + "already liked this post");
        }
    }

    public int getLikeCount() {
        return likes.size();
    }

    public List<Like> getLikes() {
        return likes;
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", author=" + author.getName() +
                ", content='" + content + '\'' +
                ", likes=" + likes.size() +
                ", comments=" + comments.size() +
                '}';
    }


}
