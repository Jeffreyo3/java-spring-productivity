package com.jeffreyorndorff.productivity.repositories;

import com.jeffreyorndorff.productivity.models.RecipeItem;
import org.springframework.data.repository.CrudRepository;

public interface RecipeItemRepository extends CrudRepository<RecipeItem, Long> {
}
