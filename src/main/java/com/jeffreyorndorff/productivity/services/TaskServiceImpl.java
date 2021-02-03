package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.Task;
import com.jeffreyorndorff.productivity.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "taskService")
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task findTaskById(long id) {
        return taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task id" +
                " " + id +
                " Not Found"));
    }
}
