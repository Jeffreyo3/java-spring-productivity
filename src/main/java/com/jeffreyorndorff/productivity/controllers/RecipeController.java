package com.jeffreyorndorff.productivity.controllers;

import com.jeffreyorndorff.productivity.models.helpermodels.SimpleRecipe;
import com.jeffreyorndorff.productivity.models.models.Recipe;
import com.jeffreyorndorff.productivity.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    /*
     * List all recipes
     */
    @GetMapping(value = "/recipes", produces = "application/JSON")
    public ResponseEntity<?> listAllRecipes() {
        List<SimpleRecipe> recipeList = recipeService.findAll();
        return new ResponseEntity<>(recipeList, HttpStatus.OK);
    }

    // TODO: Use SecurityContext to validate logged-in user once auth is added
    /*
    * Get Recipe based on ID
    * What is returned is based on whether userid == authorid
    */
    @GetMapping(value = "/recipe/{recipeId}/user/{userId}", produces = "application/JSON")
    public ResponseEntity<?> getRecipeById(@PathVariable long recipeId, @PathVariable long userId) {
        Recipe recipe = recipeService.findRecipeById(recipeId);

        // if our user is not the author, hide subscriber info
        if (recipe.getRecipeid() != userId) {
            SimpleRecipe simpleRecipe = recipeService.convertToSimpleRecipe(recipe);
            return new ResponseEntity<>(simpleRecipe, HttpStatus.OK);
        }

        // otherwise, return full recipe object
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }
}
