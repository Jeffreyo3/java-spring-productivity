package com.jeffreyorndorff.productivity.models;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryid;

    @Column(nullable = false, unique = true)
    private String category;

    public Category() {
        // Used by JPA
    }

    public Category(String category) {
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
}
