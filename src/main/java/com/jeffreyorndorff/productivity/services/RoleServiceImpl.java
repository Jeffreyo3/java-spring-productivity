package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.Role;
import com.jeffreyorndorff.productivity.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "roleService")
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository rolerepo;

    @Override
    public Role findRoleById(long id) {
        return rolerepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role id " + id + " Not Found"));
    }
}
