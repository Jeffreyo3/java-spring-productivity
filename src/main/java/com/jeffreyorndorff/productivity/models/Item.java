package com.jeffreyorndorff.productivity.models;

import javax.persistence.*;

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
}
