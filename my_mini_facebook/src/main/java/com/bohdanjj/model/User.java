package com.bohdanjj.model;
import java.util.*;

public class User {
    private int id;
    private String name;
    private String email;
    private List<User> friends;
    private List<Post> posts;

    public User (int id, String name, String email) {

        this.id = id;
        this.name =  name;
        this.email = email;
        this.friends = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public void addFriend(User friend) {
        if (!friends.contains(friend)) {
            friends.add(friend);
            friend.friends.add(this);
            System.out.println(name + " and " + friend.name + " are friends now! ");
        }
        else {
            System.out.println(" You have a friend like " + friend.name);
        }
    }

    public void createPost(String content) {
        Post newPost = new Post(this, content); // Передаємо автора та текст поста
        posts.add(newPost); // Додаємо пост до списку постів
        System.out.println(name + " created a new post: " + content);
    }

    public List<User> getFriends() {
        return friends;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", friendsCount=" + friends.size() +
                ", postsCount=" + posts.size() +
                '}';
    }



}
