package com.jeffreyorndorff.productivity.repositories;

import com.jeffreyorndorff.productivity.models.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
}
