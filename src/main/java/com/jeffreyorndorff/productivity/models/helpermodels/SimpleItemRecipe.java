package com.jeffreyorndorff.productivity.models.helpermodels;

import com.jeffreyorndorff.productivity.models.models.Recipe;

public class SimpleItemRecipe {
    private Recipe recipe;
    private float quantity;
    private String measurement;

    public SimpleItemRecipe(Recipe recipe, float quantity, String measurement) {
        this.recipe = recipe;
        this.quantity = quantity;
        this.measurement = measurement;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
}
