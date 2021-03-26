package com.jeffreyorndorff.productivity.models.helpermodels;

public class SimpleTask {
    private long taskid;
    private String task;
    private boolean completed;
    private SimpleTaskCategory category;

    public SimpleTask(long taskid, String task, boolean completed, SimpleTaskCategory category) {
        this.taskid = taskid;
        this.task = task;
        this.completed = completed;
        this.category = category;
    }

    public long getTaskid() {
        return taskid;
    }

    public void setTaskid(long taskid) {
        this.taskid = taskid;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public SimpleTaskCategory getCategory() {
        return category;
    }

    public void setCategory(SimpleTaskCategory category) {
        this.category = category;
    }
}
