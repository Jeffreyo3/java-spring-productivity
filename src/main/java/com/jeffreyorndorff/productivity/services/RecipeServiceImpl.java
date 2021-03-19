package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.helpermodels.SimpleItem;
import com.jeffreyorndorff.productivity.models.helpermodels.SimpleRecipe;
import com.jeffreyorndorff.productivity.models.helpermodels.SimpleRecipeAuthor;
import com.jeffreyorndorff.productivity.models.helpermodels.SimpleRecipeItem;
import com.jeffreyorndorff.productivity.models.models.Recipe;
import com.jeffreyorndorff.productivity.models.models.RecipeItem;
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

    @Override
    public SimpleRecipe convertToSimpleRecipe(Recipe recipe) {
        SimpleRecipeAuthor author = new SimpleRecipeAuthor(
                recipe.getAuthor().getUserid(),
                recipe.getAuthor().getUsername(),
                recipe.getAuthor().getFname(),
                recipe.getAuthor().getLname()
        );

        Set<SimpleRecipeItem> recipeItems = new HashSet<>();
        for(RecipeItem ri : recipe.getItems()) {
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
}
