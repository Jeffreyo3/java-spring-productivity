package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.helpermodels.SimpleRecipe;
import com.jeffreyorndorff.productivity.models.models.Recipe;

import java.util.List;

public interface RecipeService {
    Recipe findRecipeById(long id);

    List<Recipe> findAllByAuthorId(long authorId);

    List<SimpleRecipe> findAll();

    SimpleRecipe convertToSimpleRecipe(Recipe recipe);

    void save(Recipe recipe, long authorId);
}
