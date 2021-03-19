package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.helpermodels.SimpleRecipe;
import com.jeffreyorndorff.productivity.models.models.Recipe;

import java.util.List;

public interface RecipeService {
    Recipe findRecipeById(long id);

    List<Recipe> findAllByAuthorId(long authorId);

    List<SimpleRecipe> findAll();

    SimpleRecipe convertToSimpleRecipe(Recipe recipe);

    Recipe save(Recipe recipe, long authorId);

    void update(Recipe recipe, long authorId) throws IllegalAccessException;

    void delete(long recipeId, long authorId) throws IllegalAccessException;
}
