package com.jeffreyorndorff.productivity.models.compositeidmodels;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Class to represent the complex primary key for RecipeItem
 */
@Embeddable
public class RecipeItemId implements Serializable {

    private long recipe;
    private long item;

    public RecipeItemId() {
        // Used by JPA
    }

    public long getRecipe() {
        return recipe;
    }

    public void setRecipe(long recipe) {
        this.recipe = recipe;
    }

    public long getItem() {
        return item;
    }

    public void setItem(long item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeItemId that = (RecipeItemId) o;
        return recipe == that.recipe && item == that.item;
    }

    @Override
    public int hashCode() {
        return 37;
    }
}
