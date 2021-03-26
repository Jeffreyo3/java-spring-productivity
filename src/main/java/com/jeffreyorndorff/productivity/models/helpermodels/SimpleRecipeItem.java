package com.jeffreyorndorff.productivity.models.helpermodels;

public class SimpleRecipeItem {
    private SimpleItem item;
    private float quantity;
    private String measurement;

    public SimpleRecipeItem(SimpleItem item, float quantity, String measurement) {
        this.item = item;
        this.quantity = quantity;
        this.measurement = measurement;
    }

    public SimpleItem getItem() {
        return item;
    }

    public void setItem(SimpleItem item) {
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
}
