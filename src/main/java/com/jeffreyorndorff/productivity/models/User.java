package com.jeffreyorndorff.productivity.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @Column(nullable = false)
    private String username;

    // TODO - hash and save hashed password, not user input
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fname;

    @Column(nullable = false)
    private String lname;

    @Email
    private String email;

    /**
     * Part of the join relationship between user and role
     * connects users to the user role combination
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private Set<UserRole> roles = new HashSet<>();

    /**
     * Part of the join relationship between user and item
     * connects users to the items combination
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private Set<UserItem> items = new HashSet<>();

    /**
     * Part of the join relationship between user and subscribedRecipe
     * connects users to the recipes combination
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private Set<UserSubscribedRecipe> subscribedRecipes = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private Set<Task> tasks = new HashSet<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "author", allowSetters = true)
    private Set<Recipe> recipes = new HashSet<>();

    public User() {
        // used by JPA
    }

    public User(String username, String password, String fname, String lname, String email) {
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<UserItem> getItems() {
        return items;
    }

    public void setItems(Set<UserItem> items) {
        this.items = items;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipies) {
        this.recipes = recipies;
    }

    public Set<UserSubscribedRecipe> getSubscribedRecipes() {
        return subscribedRecipes;
    }

    public void setSubscribedRecipes(Set<UserSubscribedRecipe> subscribedRecipes) {
        this.subscribedRecipes = subscribedRecipes;
    }
}
