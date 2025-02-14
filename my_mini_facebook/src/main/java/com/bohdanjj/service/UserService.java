package com.bohdanjj.service;

import com.bohdanjj.model.User;
import com.bohdanjj.model.Post;

import java.util.*;

public class UserService {

    private final Map<Integer, User> users = new HashMap<>();

    public void assUser(User user) {
        if (!users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            System.out.println("User" + user.getName() + " added successfully");
        }
        else {
            System.out.println("User with ID" + user.getId() + "already exists");
        }
    }

    public User getUserById(int id) {
        return users.get(id);
    }

    public void addFriend(int userId, int friendId) {
        User user = getUserById(userId);
        User friend = getUserById(friendId);

        if (user == null || friend == null) {
            System.out.println("One of the users doesnt exists");
            return;
        }

        user.addFriend(friend);
    }

    public List<User> getFriends(int userId) {
        User user = getUserById(userId);
        if (user != null) {
            return user.getFriends();
        }

        return Collections.emptyList();

    }

    public void listAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users available ");
        }
        else {
            users.values().forEach(user -> System.out.println("ID: " + user.getId() + ", Name: " + user.getName()));
        }
    }

}
