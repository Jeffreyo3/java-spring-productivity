package com.jeffreyorndorff.productivity.models.helpermodels;

import com.jeffreyorndorff.productivity.models.models.UserSubscribedRecipe;

import java.util.Set;

public class SimpleRecipe {
    private long recipeid;
    private String recipe;
    private String instructions;
    private SimpleRecipeAuthor author;
    private Set<SimpleRecipeItem> items;
    private int subscriberCount;

    public SimpleRecipe(long recipeid, String recipe, String instructions, SimpleRecipeAuthor author, Set<SimpleRecipeItem> items, Set<UserSubscribedRecipe> subscribedUsers) {
        this.recipeid = recipeid;
        this.recipe = recipe;
        this.instructions = instructions;
        this.author = author;
        this.items = items;
        this.subscriberCount = 0;
        for (UserSubscribedRecipe usr : subscribedUsers) {
            this.subscriberCount += 1;
        }
    }

    public long getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(long recipeid) {
        this.recipeid = recipeid;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public SimpleRecipeAuthor getAuthor() {
        return author;
    }

    public void setAuthor(SimpleRecipeAuthor author) {
        this.author = author;
    }

    public Set<SimpleRecipeItem> getItems() {
        return items;
    }

    public void setItems(Set<SimpleRecipeItem> items) {
        this.items = items;
    }

    public int getSubscriberCount() {
        return subscriberCount;
    }

    public void setSubscriberCount(int subscriberCount) {
        this.subscriberCount = subscriberCount;
    }

    public void recalculateSubscriberCount(Set<UserSubscribedRecipe> subscribedUsers) {
        this.subscriberCount = 0;
        for (UserSubscribedRecipe usr : subscribedUsers) {
            this.subscriberCount += 1;
        }
    }
}
