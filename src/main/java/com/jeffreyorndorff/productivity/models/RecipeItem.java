package com.jeffreyorndorff.productivity.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jeffreyorndorff.productivity.helpermodels.RecipeItemId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "recipeitems")
@IdClass(RecipeItemId.class)
public class RecipeItem extends Auditable implements Serializable {

    /**
     * 1/2 of the primary key (long) for recipeitems.
     * Also is a foreign key into the recipes table
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "recipeid")
    @JsonIgnoreProperties(value = {"items", "users"}, allowSetters = true)
    private Recipe recipe;

    /**
     * 1/2 of the primary key (long) for recipeitems.
     * Also is a foreign key into the items table
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "itemid")
    @JsonIgnoreProperties(value = {"recipes", "users"}, allowSetters = true)
    private Item item;

    @Column(nullable = false)
    private float quantity;

    @Column(nullable = false)
    private String measurement;

    public RecipeItem() {
        // Used by JPA
    }

    public RecipeItem(Recipe recipe, Item item, float quantity, String measurement) {
        this.recipe = recipe;
        this.item = item;
        this.quantity = quantity;
        this.measurement = measurement;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecipeItem)) {
            return false;
        }
        RecipeItem that = (RecipeItem) o;
        return ((recipe == null) ? 0 : recipe.getRecipeid()) == ((that.recipe == null) ? 0 :
                that.recipe.getRecipeid()) &&
                ((item == null) ? 0 : item.getItemid()) == ((that.item == null) ? 0 :
                        that.item.getItemid());
    }

    @Override
    public int hashCode() {
        return 37;
    }
}
