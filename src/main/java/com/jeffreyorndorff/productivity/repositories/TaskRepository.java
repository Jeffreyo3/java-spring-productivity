package com.jeffreyorndorff.productivity.repositories;

import com.jeffreyorndorff.productivity.models.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
