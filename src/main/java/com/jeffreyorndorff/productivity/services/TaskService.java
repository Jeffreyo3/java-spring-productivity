package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.helpermodels.SimpleTask;
import com.jeffreyorndorff.productivity.models.models.Task;

import java.util.List;

public interface TaskService {
    Task findTaskById(long id);

    SimpleTask findSimpleTaskById(long id);

    List<SimpleTask> findAll();

    List<SimpleTask> findByUserId(long userId);

    SimpleTask convertTaskToSimpleTask(Task task);

    SimpleTask save(SimpleTask newTask, long userId);

    void toggleComplete(long taskId);

    void update(long taskId, SimpleTask updatedTask);

    void delete(long taskId);
}
