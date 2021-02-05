package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.helpermodels.SimpleTask;
import com.jeffreyorndorff.productivity.models.Task;

import java.util.List;

public interface TaskService {
    SimpleTask findTaskById(long id);

    List<SimpleTask> findAll();

    List<SimpleTask> findByUserId(long userId);

    SimpleTask convertTaskToSimpleTask(Task task);
}
