package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.Task;

public interface TaskService {
    Task findTaskById(long id);
}
