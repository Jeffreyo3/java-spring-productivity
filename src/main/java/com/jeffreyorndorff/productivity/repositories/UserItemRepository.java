package com.jeffreyorndorff.productivity.repositories;

import com.jeffreyorndorff.productivity.models.User;
import com.jeffreyorndorff.productivity.models.UserItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserItemRepository extends CrudRepository<UserItem, Long> {
    List<UserItem> findAllByUser(User user);
}
