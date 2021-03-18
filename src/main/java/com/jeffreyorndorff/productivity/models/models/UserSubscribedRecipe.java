package com.jeffreyorndorff.productivity.models.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jeffreyorndorff.productivity.models.compositeidmodels.UserSubscribedRecipeId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usersubscribedrecipes")
@IdClass(UserSubscribedRecipeId.class)
public class UserSubscribedRecipe extends Auditable implements Serializable {

    /**
     * 1/2 of the primary key (long) for usersubscribedrecipes.
     * Also is a foreign key into the users table
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = {"recipes", "users", "subscribedRecipes", "roles", "tasks",
            "items",
            "email",
            "password"}, allowSetters = true)
    private User user;

    /**
     * 1/2 of the primary key (long) for usersubscribedrecipes.
     * Also is a foreign key into the recipes table
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "recipeid")
    @JsonIgnoreProperties(value = {"users", "subscribedRecipes", "recipes"}, allowSetters = true)
    private Recipe recipe;

    public UserSubscribedRecipe() {
        // Used by JPA
    }

    public UserSubscribedRecipe(User user, Recipe recipe) {
        this.user = user;
        this.recipe = recipe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserSubscribedRecipe)) {
            return false;
        }
        UserSubscribedRecipe that = (UserSubscribedRecipe) o;
        return ((user == null) ? 0 : user.getUserid()) == ((that.user == null) ? 0 : that.user.getUserid()) &&
                ((recipe == null) ? 0 : recipe.getRecipeid()) == ((that.recipe == null) ? 0 :
                        that.recipe.getRecipeid());
    }

    @Override
    public int hashCode() {
        return 37;
    }
}
