package com.bohdanjj.controller;

import com.bohdanjj.dto.userDTO;
import com.bohdanjj.exception.GlobalExceptionHandler;
import com.bohdanjj.model.User;
import com.bohdanjj.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody userDTO userDTO) {
        User newUser = new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
        userService.addUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/{userID}")
    public ResponseEntity<User> getUserById(@PathVariable int userID) {
        User user = findUserById(userID);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/{userID}/addFriend")
    public ResponseEntity<Void> addFriend(@PathVariable int userId, @RequestParam int friendId) {
        findUserById(userId);
        findUserById(friendId);
        userService.addFriend(userId, friendId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{userID}/friends")
    public ResponseEntity<List<User>> listFriends(@PathVariable int userID) {
        findUserById(userID);
        List<User> friends = userService.getFriends(userID);
        if (friends.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(friends);
    }

    @GetMapping
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(users);
    }

    private User findUserById(int userID) {
        User user = userService.getUserById(userID);
        if (user == null) {
            throw new IllegalArgumentException("User with ID: " + userID + " not found");
        }
        return user;
    }
}
