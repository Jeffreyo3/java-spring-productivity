package com.jeffreyorndorff.productivity.controllers;

import com.jeffreyorndorff.productivity.models.helpermodels.SimpleRecipe;
import com.jeffreyorndorff.productivity.models.models.Recipe;
import com.jeffreyorndorff.productivity.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
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

    // TODO: Use SecurityContext to validate logged-in user once auth is added
    /*
     * Get all recipes by a given author
     * What is returned is based on whether userid == authorid
     */
    @GetMapping(value = "/author/{authorId}/user/{userId}", produces = "application/JSON")
    public ResponseEntity<?> findAllRecipesByAuthorId(@PathVariable long authorId, @PathVariable long userId) {
        List<Recipe> recipes = recipeService.findAllByAuthorId(authorId);

        // if our user is not the author, hide subscriber info
        if (authorId != userId) {
            List<SimpleRecipe> simpleRecipes = new ArrayList<>();
            for (Recipe r : recipes) {
                simpleRecipes.add(recipeService.convertToSimpleRecipe(r));
            }

            return new ResponseEntity<>(simpleRecipes, HttpStatus.OK);
        }

        // otherwise, return full recipe objects
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    // TODO: Once authentication is added, use SecurityContext to grab userId for the authorId
    /*
     * Create new recipe
     */
    @PostMapping(value = "/recipe/author/{authorId}", consumes = "application/JSON")
    public ResponseEntity<?> createRecipe(@PathVariable long authorId, @Valid @RequestBody Recipe recipe) {
        recipe.setRecipeid(0);
        recipe = recipeService.save(recipe, authorId);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRecipeURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{recipeId}")
                .buildAndExpand(recipe.getRecipeid())
                .toUri();
        responseHeaders.setLocation(newRecipeURI);

        return new ResponseEntity<>(recipe, responseHeaders, HttpStatus.CREATED);
    }

    // TODO: Once authentication is added, use SecurityContext to validate author
    /*
     * Update recipe by recipe.
     */
    @PatchMapping(value = "/recipe/author/{authorId}", consumes = "application/JSON")
    public ResponseEntity<?> updateRecipe(@PathVariable long authorId, @Valid @RequestBody Recipe recipe) throws IllegalAccessException {
        recipeService.update(recipe, authorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // TODO: Once authentication is added, use SecurityContext to validate author
    /*
    * Author delete recipe by id
    */
    @DeleteMapping(value = "/recipe/{recipeId}/author/{authorId}")
    public ResponseEntity<?> deleteRecipeById(@PathVariable long recipeId, @PathVariable long authorId) throws IllegalAccessException {
        recipeService.delete(recipeId, authorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
