package com.bohdanjj.model;

public class Comment {
    private int id;
    private static int commentCounter = 0;
    private String text;
    private User author;

    public Comment(String text, User author) {
        this.id = ++commentCounter;
        this.author = author;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public void setText(String newText) {
        this.text = newText;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author=" + author.getName() +
                ", text='" + text + '\'' +
                '}';
    }
}
