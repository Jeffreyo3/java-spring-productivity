package com.jeffreyorndorff.productivity.repositories;

import com.jeffreyorndorff.productivity.models.UserSubscribedRecipe;
import org.springframework.data.repository.CrudRepository;

public interface UserSubscribedRecipeRepository extends CrudRepository<UserSubscribedRecipe, Long> {
}
