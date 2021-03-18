package com.jeffreyorndorff.productivity.models.helpermodels;

public class SimpleTaskCategory {
    private long categoryid;
    private String category;

    public SimpleTaskCategory(long categoryid, String category) {
        this.categoryid = categoryid;
        this.category = category;
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
        this.category = category;
    }
}
