package com.jeffreyorndorff.productivity.helpermodels;

public class SimpleItem {
    private long itemid;
    private String item;
    private float price;
    private String url;

    public SimpleItem(long itemid, String item, float price, String url) {
        this.itemid = itemid;
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
