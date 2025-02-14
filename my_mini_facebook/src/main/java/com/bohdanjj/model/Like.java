package com.bohdanjj.model;

public class Like {
    private int id;
    private static int likeCounter = 0;
    private User user;


    public Like(User user) {
        this.id = ++likeCounter;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", user=" + user.getName() +
                '}';
    }

}
