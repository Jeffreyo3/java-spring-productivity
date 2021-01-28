package com.jeffreyorndorff.productivity.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipe extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long recipeid;

    @Column(nullable = false)
    private String recipe;

    @Column(nullable = false, length = 10000000)
    private String instructions;

    @ManyToOne
    @JoinColumn(name = "authorid")
    @JsonIgnoreProperties("recipes")
    private User author;

    /**
     * Part of the join relationship between item and recipe
     * connects recipies to the items combination
     */
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "recipe", allowSetters = true)
    private Set<RecipeItem> items = new HashSet<>();

    /**
     * Part of the join relationship between recipe and subscribedUser
     * connects recipes to the users combination
     */
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "recipe", allowSetters = true)
    private Set<UserSubscribedRecipe> subscribedUsers = new HashSet<>();

    public Recipe() {
        // Used by JPA
    }

    public Recipe(String recipe, String instructions, User author) {
        this.recipe = recipe;
        this.instructions = instructions;
        this.author = author;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<RecipeItem> getItems() {
        return items;
    }

    public void setItems(Set<RecipeItem> items) {
        this.items = items;
    }

    public Set<UserSubscribedRecipe> getSubscribedUsers() {
        return subscribedUsers;
    }

    public void setSubscribedUsers(Set<UserSubscribedRecipe> subscribedUsers) {
        this.subscribedUsers = subscribedUsers;
    }
}
