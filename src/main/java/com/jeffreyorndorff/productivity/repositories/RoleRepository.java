package com.jeffreyorndorff.productivity.repositories;

import com.jeffreyorndorff.productivity.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
