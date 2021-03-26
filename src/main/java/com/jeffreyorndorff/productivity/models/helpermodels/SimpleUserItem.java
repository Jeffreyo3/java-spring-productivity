package com.jeffreyorndorff.productivity.models.helpermodels;

public class SimpleUserItem {
    private SimpleItem item;
    private int quantity;
    private boolean checked;
    private String notes;

    public SimpleUserItem(SimpleItem item, int quantity, boolean checked, String notes) {
        this.item = item;
        this.quantity = quantity;
        this.checked = checked;
        this.notes = notes;
    }

    public SimpleItem getItem() {
        return item;
    }

    public void setItem(SimpleItem item) {
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
}
