package com.jeffreyorndorff.productivity.repositories;

import com.jeffreyorndorff.productivity.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
