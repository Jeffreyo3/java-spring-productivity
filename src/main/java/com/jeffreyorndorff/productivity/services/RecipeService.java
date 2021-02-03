package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.Recipe;

public interface RecipeService {
    Recipe findRecipeById(long id);
}
