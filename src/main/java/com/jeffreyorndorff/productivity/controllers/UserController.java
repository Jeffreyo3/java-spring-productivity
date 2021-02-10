package com.jeffreyorndorff.productivity.controllers;

import com.jeffreyorndorff.productivity.helpermodels.CreatedUser;
import com.jeffreyorndorff.productivity.models.User;
import com.jeffreyorndorff.productivity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
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

    @PostMapping(value = "/user", consumes = "application/JSON")
    public ResponseEntity<?> addNewUser(@Valid
                                        @RequestBody User newuser) throws URISyntaxException {
        newuser.setUserid(0);
        newuser = userService.save(newuser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newuser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(new CreatedUser(newuser.getUserid(), newuser.getUsername()),
                responseHeaders,
                HttpStatus.CREATED);
    }

    // TODO: Refactor once Security is implemented to grab userId from SecurityContext
    @PatchMapping(value = "/user/{userId}", consumes = "application/JSON")
    public ResponseEntity<?> updateUser(@RequestBody User updateUser,
                                        @PathVariable long userId) {
        userService.update(updateUser, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // TODO: Refactor once Security is implemented to grab userId from SecurityContext
    @DeleteMapping(value = "/user/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable long userId) {
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
