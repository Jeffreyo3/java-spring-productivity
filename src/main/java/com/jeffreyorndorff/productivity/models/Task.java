package com.jeffreyorndorff.productivity.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long taskid;

    @Column(nullable = false)
    private String task;

    private boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("tasks")
    private User user;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    @JsonIgnoreProperties("tasks")
    private SimpleCategory category;

    public Task() {
        // Used by JPA
    }

    public Task(User user, String task, SimpleCategory category) {
        this.task = task;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SimpleCategory getCategory() {
        return category;
    }

    public void setCategory(SimpleCategory category) {
        this.category = category;
    }
}
