package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.User;

import java.util.List;

public interface UserService {
    User findUserById(long id);

    List<User> findAll();

    User findByUsername(String username);

    List<User> findByNameContaining(String substring);

    User save(User user);

    User update(User user, long id);

    void delete(long id);
}
