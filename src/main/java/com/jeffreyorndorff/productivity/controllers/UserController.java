package com.jeffreyorndorff.productivity.controllers;

import com.jeffreyorndorff.productivity.models.User;
import com.jeffreyorndorff.productivity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users", produces = "application/JSON")
    public ResponseEntity<?> listAllUsers() {
        List<User> userList = userService.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}", produces = "application/JSON")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        User user = userService.findUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/name/{userName}", produces = "application/JSON")
    public ResponseEntity<?> getUserByName(@PathVariable String userName) {
        User user = userService.findByUsername(userName);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{substring}", produces = "application/JSON")
    public ResponseEntity<?> getUsersBySubstring(@PathVariable String substring) {
        List<User> users = userService.findByNameContaining(substring);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
