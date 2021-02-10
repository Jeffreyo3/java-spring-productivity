package com.jeffreyorndorff.productivity.controllers;

import com.jeffreyorndorff.productivity.helpermodels.SimpleTask;
import com.jeffreyorndorff.productivity.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    /*
    * List all tasks
    */
    @GetMapping(value = "/tasks", produces = "application/JSON")
    public ResponseEntity<?> listAllTasks() {
        List<SimpleTask> taskList = taskService.findAll();
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    /*
    * Get a single task by its id
    */
    @GetMapping(value = "/task/{taskId}", produces = "application/JSON")
    public ResponseEntity<?> getTaskById(@PathVariable long taskId) {
        SimpleTask task = taskService.findSimpleTaskById(taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    // TODO: Refactor once Security is implemented to grab userId from SecurityContext
    /*
    * Get all tasks from a given user
    */
    @GetMapping(value = "/user/{userId}", produces = "application/JSON")
    public ResponseEntity<?> getTasksByUserId(@PathVariable long userId) {
        List<SimpleTask> tasks = taskService.findByUserId(userId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // TODO: Refactor once Security is implemented to grab userId from SecurityContext
    /*
    * Create a new task
    */
    @PostMapping(value = "/user/{userId}", produces = "application/JSON")
    public ResponseEntity<?> addNewTask(@Valid
                                        @RequestBody SimpleTask newTask,
                                        @PathVariable long userId) {
        newTask.setTaskid(0);
        newTask = taskService.save(newTask, userId);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newTaskURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{taskid}")
                .buildAndExpand(newTask.getTaskid())
                .toUri();
        responseHeaders.setLocation(newTaskURI);

        return new ResponseEntity<>(newTask,
                responseHeaders,
                HttpStatus.CREATED);
    }

    @PatchMapping(value = "/task/{taskId}/toggle")
    public ResponseEntity<?> toggleComplete(@PathVariable long taskId) {
        taskService.toggleComplete(taskId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
