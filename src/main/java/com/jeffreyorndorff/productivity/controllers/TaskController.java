package com.jeffreyorndorff.productivity.controllers;

import com.jeffreyorndorff.productivity.helpermodels.SimpleTask;
import com.jeffreyorndorff.productivity.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/tasks", produces = "application/JSON")
    public ResponseEntity<?> listAllTasks() {
        List<SimpleTask> taskList = taskService.findAll();
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @GetMapping(value = "/task/{taskId}", produces = "application/JSON")
    public ResponseEntity<?> getTaskById(@PathVariable long taskId) {
        SimpleTask task = taskService.findTaskById(taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}", produces = "application/JSON")
    public ResponseEntity<?> getTasksByUserId(@PathVariable long userId) {
        List<SimpleTask> tasks = taskService.findByUserId(userId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}
