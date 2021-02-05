package com.jeffreyorndorff.productivity.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class SimpleCategory extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryid;

    @Column(nullable = false, unique = true)
    private String category;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"category", "user"}, allowSetters = true)
    private Set<Task> tasks = new HashSet<>();


    public SimpleCategory() {
        // Used by JPA
    }

    public SimpleCategory(String category) {
        this.category = category.toUpperCase();
    }

    public long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(long categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category.toUpperCase();
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
