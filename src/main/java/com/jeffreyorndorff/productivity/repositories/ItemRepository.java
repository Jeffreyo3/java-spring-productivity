package com.jeffreyorndorff.productivity.repositories;

import com.jeffreyorndorff.productivity.models.models.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
