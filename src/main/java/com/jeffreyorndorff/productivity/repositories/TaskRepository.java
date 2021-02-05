package com.jeffreyorndorff.productivity.repositories;

import com.jeffreyorndorff.productivity.models.Task;
import com.jeffreyorndorff.productivity.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
        List<Task> findByUser(User user);
}
