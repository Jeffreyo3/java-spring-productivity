package com.jeffreyorndorff.productivity.repositories;

import com.jeffreyorndorff.productivity.models.models.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    List<Recipe> findAllByAuthor_Userid(long userid);
}
