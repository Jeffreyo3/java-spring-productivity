package com.jeffreyorndorff.productivity.repositories;

import com.jeffreyorndorff.productivity.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsernameIgnoringCase(String username);

    List<User> findByUsernameContainingIgnoringCase(String toLowerCase);
}
