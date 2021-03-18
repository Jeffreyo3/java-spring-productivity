package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.models.Recipe;
import com.jeffreyorndorff.productivity.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "recipeService")
public class RecipeServiceImpl implements RecipeService{
    @Autowired
    private RecipeRepository reciperepo;

    @Override
    public Recipe findRecipeById(long id) {
        return reciperepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Recipe id " + id +
                " Not Found"));
    }
}
