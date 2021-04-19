package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.helpermodels.SimpleTask;
import com.jeffreyorndorff.productivity.models.helpermodels.SimpleTaskCategory;
import com.jeffreyorndorff.productivity.models.models.Category;
import com.jeffreyorndorff.productivity.models.models.Task;
import com.jeffreyorndorff.productivity.models.models.User;
import com.jeffreyorndorff.productivity.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "taskService")
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Override
    public Task findTaskById(long id) {
        return taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Task" + " id" + " " + id + " Not Found"));
    }

    @Override
    public SimpleTask convertTaskToSimpleTask(Task task) {
        return new SimpleTask(
                task.getTaskid(),
                task.getTask(),
                task.isCompleted(),
                new SimpleTaskCategory(
                        task.getCategory().getCategoryid(),
                        task.getCategory().getCategory()
                ));
    }

    @Override
    public SimpleTask findSimpleTaskById(long id) {
        Task task = findTaskById(id);

        return convertTaskToSimpleTask(task);
    }

    @Override
    public List<SimpleTask> findAll() {
        List<SimpleTask> list = new ArrayList<>();

        taskRepository.findAll().iterator().forEachRemaining(listItem ->
                list.add(convertTaskToSimpleTask(listItem)));

        return list;
    }

    @Override
    public List<SimpleTask> findByUserId(long userId) {
        User user = userService.findUserById(userId);

        List<SimpleTask> list = new ArrayList<>();

        taskRepository.findByUser_Userid(user.getUserid()).iterator().forEachRemaining(listItem ->
                list.add(convertTaskToSimpleTask(listItem)));

        return list;
    }

    @Transactional
    @Override
    public SimpleTask save(SimpleTask newTask, long userId) {
        User user = userService.findUserById(userId);
        Category category =
                categoryService.findCategoryByName(newTask.getCategory().getCategory());
        if(category == null) {
            category =
                    categoryService.save(new Category(newTask.getCategory().getCategory()));
        }
        Task task = taskRepository.save(new Task(user, newTask.getTask(), category));
        newTask = new SimpleTask(task.getTaskid(), task.getTask(), task.isCompleted(),
                new SimpleTaskCategory(task.getCategory().getCategoryid(),
                        task.getCategory().getCategory()));

        return newTask;
    }

    @Transactional
    @Override
    public void toggleComplete(long taskId) {
        Task task = findTaskById(taskId);

        task.setCompleted(!task.isCompleted());
    }

    @Transactional
    @Override
    public void update(long taskId, SimpleTask updatedTask) {
        Task task = findTaskById(taskId);

        if(updatedTask.getTask() != null) {
            task.setTask(updatedTask.getTask());
        }

        if(!Boolean.toString(updatedTask.isCompleted()).equals("")) {
            task.setCompleted(updatedTask.isCompleted());
        }

        if(updatedTask.getCategory() != null) {
            Category category =
                    categoryService.findCategoryByName(updatedTask.getCategory().getCategory());
            if(category == null) {
                category =
                        categoryService.save(new Category(updatedTask.getCategory().getCategory()));
            }

            task.setCategory(category);
        }

        taskRepository.save(task);
    }

    @Transactional
    @Override
    public void delete(long taskId) {
        if(taskRepository.findById(taskId).isPresent()) {
            taskRepository.deleteById(taskId);
        } else {
            throw new EntityNotFoundException("Task with id " + taskId + " Not Found!");
        }
    }
}
