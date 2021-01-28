package com.jeffreyorndorff.productivity.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserSubscribedRecipeId implements Serializable {

    private long user;
    private long recipe;

    public UserSubscribedRecipeId() {
        // Used by JPA
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public long getRecipe() {
        return recipe;
    }

    public void setRecipe(long recipe) {
        this.recipe = recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSubscribedRecipeId that = (UserSubscribedRecipeId) o;
        return user == that.user && recipe == that.recipe;
    }

    @Override
    public int hashCode() {
        return 37;
    }
}
