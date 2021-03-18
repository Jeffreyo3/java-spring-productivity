package com.jeffreyorndorff.productivity.models.helpermodels;

import java.util.Set;

public class SimpleItemWithRecipes {
    private long itemid;
    private String item;
    private float price;
    private String url;
    private Set<SimpleItemRecipe> recipes;

    public SimpleItemWithRecipes(long itemid, String item, float price, String url, Set<SimpleItemRecipe> recipes) {
        this.itemid = itemid;
        this.item = item;
        this.price = price;
        this.url = url;
        this.recipes = recipes;
    }

    public long getItemid() {
        return itemid;
    }

    public void setItemid(long itemid) {
        this.itemid = itemid;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<SimpleItemRecipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<SimpleItemRecipe> recipes) {
        this.recipes = recipes;
    }
}
