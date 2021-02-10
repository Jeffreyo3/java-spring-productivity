package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.helpermodels.SimpleTask;
import com.jeffreyorndorff.productivity.helpermodels.SimpleTaskCategory;
import com.jeffreyorndorff.productivity.models.SimpleCategory;
import com.jeffreyorndorff.productivity.models.Task;
import com.jeffreyorndorff.productivity.models.User;
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
    public SimpleTask findTaskById(long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Task" +
                " id" +
                " " + id +
                " Not Found"));

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

    @Override
    public SimpleTask save(SimpleTask newTask, long userId) {
        User user = userService.findUserById(userId);
        SimpleCategory category =
                categoryService.findCategoryByName(newTask.getCategory().getCategory());
        if(category == null) {
            category =
                    categoryService.save(new SimpleCategory(newTask.getCategory().getCategory()));
        }
        Task task = taskRepository.save(new Task(user, newTask.getTask(), category));
        newTask = new SimpleTask(task.getTaskid(), task.getTask(), task.isCompleted(),
                new SimpleTaskCategory(task.getCategory().getCategoryid(),
                        task.getCategory().getCategory()));

        return newTask;
    }
}
