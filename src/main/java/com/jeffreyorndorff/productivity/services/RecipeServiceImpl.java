package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.helpermodels.SimpleItem;
import com.jeffreyorndorff.productivity.models.helpermodels.SimpleRecipe;
import com.jeffreyorndorff.productivity.models.helpermodels.SimpleRecipeAuthor;
import com.jeffreyorndorff.productivity.models.helpermodels.SimpleRecipeItem;
import com.jeffreyorndorff.productivity.models.models.*;
import com.jeffreyorndorff.productivity.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service(value = "recipeService")
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepository reciperepo;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Override
    public SimpleRecipe convertToSimpleRecipe(Recipe recipe) {
        SimpleRecipeAuthor author = new SimpleRecipeAuthor(
                recipe.getAuthor().getUserid(),
                recipe.getAuthor().getUsername(),
                recipe.getAuthor().getFname(),
                recipe.getAuthor().getLname()
        );

        Set<SimpleRecipeItem> recipeItems = new HashSet<>();
        for (RecipeItem ri : recipe.getItems()) {
            SimpleItem item = new SimpleItem(
                    ri.getItem().getItemid(),
                    ri.getItem().getItem(),
                    ri.getItem().getPrice(),
                    ri.getItem().getUrl()
            );

            SimpleRecipeItem recipeItem = new SimpleRecipeItem(
                    item,
                    ri.getQuantity(),
                    ri.getMeasurement()
            );
            recipeItems.add(recipeItem);
        }

        return new SimpleRecipe(
                recipe.getRecipeid(),
                recipe.getRecipe(),
                recipe.getInstructions(),
                author,
                recipeItems,
                recipe.getSubscribedUsers()
        );
    }

    @Override
    public Recipe findRecipeById(long id) {
        return reciperepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Recipe id " + id +
                " Not Found"));
    }

    @Override
    public List<SimpleRecipe> findAll() {
        List<SimpleRecipe> list = new ArrayList<>();
        reciperepo.findAll().iterator().forEachRemaining((listItem) ->
                list.add(convertToSimpleRecipe(listItem))
        );

        return list;
    }

    @Override
    public List<Recipe> findAllByAuthorId(long authorId) {
        List<Recipe> list = new ArrayList<>();
        reciperepo.findAllByAuthor_Userid(authorId).iterator().forEachRemaining(list::add);

        return list;
    }

    @Transactional
    @Override
    public Recipe save(Recipe recipe, long authorId) {
        User author = userService.findUserById(authorId);

        Recipe newRecipe = new Recipe();
        newRecipe.setRecipe(recipe.getRecipe());
        newRecipe.setInstructions(recipe.getInstructions());
        newRecipe.setAuthor(author);

        newRecipe.getItems().clear();
        for (RecipeItem ri : recipe.getItems()) {
            Item item = itemService.findItemById(ri.getItem().getItemid());

            RecipeItem newRI = new RecipeItem(
                    newRecipe,
                    item,
                    ri.getQuantity(),
                    ri.getMeasurement()
            );

            newRecipe.getItems().add(newRI);
        }

        // new recipe will default to only subscriber being author
        newRecipe.getSubscribedUsers().clear();
        newRecipe.getSubscribedUsers().add(
                new UserSubscribedRecipe(author, newRecipe)
        );

        return reciperepo.save(newRecipe);
    }

    @Transactional
    @Override
    public void update(Recipe recipe, long authorId) throws IllegalAccessException {
        User author = userService.findUserById(authorId);
        Recipe recipeToUpdate = findRecipeById(recipe.getRecipeid());

        if (author.getUserid() != recipeToUpdate.getAuthor().getUserid()) {
            throw new IllegalAccessException("User " + authorId + " is unauthorized to make changes to recipe with id " + recipe.getRecipeid());
        }

        recipe.setAuthor(author);
        if (recipe.getRecipe() != null) {
            recipeToUpdate.setRecipe(recipe.getRecipe());
        }
        if (recipe.getInstructions() != null) {
            recipeToUpdate.setInstructions(recipe.getInstructions());
        }
        if (recipe.getItems().size() > 0) {
            recipeToUpdate.getItems().clear();
            for (RecipeItem ri : recipe.getItems()) {
                Item item = itemService.findItemById(ri.getItem().getItemid());
                RecipeItem recipeItem = new RecipeItem(
                        recipeToUpdate,
                        item,
                        ri.getQuantity(),
                        ri.getMeasurement()
                );
                recipeToUpdate.getItems().add(recipeItem);
            }
        }

        // recipeToUpdate.get/setSubscribedUsers() not included b/c
        // author should not be able to change subscribers
        reciperepo.save(recipeToUpdate);
    }

    @Transactional
    @Override
    public void delete(long recipeId, long authorId) throws IllegalAccessException {
        Recipe toDelete = findRecipeById(recipeId);
        if (toDelete.getAuthor().getUserid() == authorId ) {
            reciperepo.deleteById(toDelete.getRecipeid());
        } else {
            throw new IllegalAccessException("User " + authorId + " is unauthorized to make changes to recipe with id " + toDelete.getRecipeid());
        }
    }
}
