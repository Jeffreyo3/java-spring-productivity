package com.jeffreyorndorff.productivity.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "items")
public class Item extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemid;

    @Column(nullable = false)
    private String item;

    @Column(nullable = false)
    private float price;

    private String url;

    /**
     * Part of the join relationship between item and user
     * connects items to the users combination
     */
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "item", allowSetters = true)
    private Set<UserItem> users = new HashSet<>();

    public Item() {
        // Used by JPA
    }

    public Item(String item, float price, String url) {
        this.item = item;
        this.price = price;
        this.url = url;
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

    public Set<UserItem> getUsers() {
        return users;
    }

    public void setUsers(Set<UserItem> users) {
        this.users = users;
    }
}
