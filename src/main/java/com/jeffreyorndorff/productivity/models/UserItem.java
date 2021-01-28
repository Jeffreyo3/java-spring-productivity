package com.jeffreyorndorff.productivity.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * the useritems table is going to represent a shopping list
 */
@Entity
@Table(name = "useritems")
@IdClass(UserItemId.class)
public class UserItem extends Auditable implements Serializable {
    /**
     * 1/2 of the primary key (long) for useritems.
     * Also is a foreign key into the users table
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "items", allowSetters = true)
    private User user;

    /**
     * 1/2 of the primary key (long) for useritems.
     * Also is a foreign key into the items table
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "itemid")
    @JsonIgnoreProperties(value = "users", allowSetters = true)
    private Item item;

    @Column(nullable = false)
    private int quantity;

    private boolean checked = false;

    @Column(nullable = true)
    private String notes;

    public UserItem() {
    }

    public UserItem(User user, Item item, int quantity, String notes) {
        this.user = user;
        this.item = item;
        this.quantity = quantity;
        this.notes = notes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserItem)) {
            return false;
        }
        UserItem that = (UserItem) o;
        return ((user == null) ? 0 : user.getUserid()) == ((that.user == null) ? 0 : that.user.getUserid()) &&
                ((item == null) ? 0 : item.getItemid()) == ((that.item == null) ? 0 :
                        that.item.getItemid());
    }

    @Override
    public int hashCode() {
        return 37;
    }
}
