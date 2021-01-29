package com.jeffreyorndorff.productivity.repositories;

import com.jeffreyorndorff.productivity.models.UserItem;
import org.springframework.data.repository.CrudRepository;

public interface UserItemRepository extends CrudRepository<UserItem, Long> {
}
