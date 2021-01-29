package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.User;
import com.jeffreyorndorff.productivity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userrepo;

    @Override
    public User findUserById(long id) {
        return userrepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User " + id + " Not Found"));
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();

        userrepo.findAll().iterator().forEachRemaining(list::add);

        return list;
    }

    @Override
    public List<User> findByUsername(String username) {
        return userrepo.findByUsernameIgnoringCase(username);
    }

//    @Override
//    public List<User> findByNameContaining(String substring) {
//        return null;
//    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user, long id) {
        return null;
    }

    @Transactional
    @Override
    public void delete(long id) {
        if(userrepo.findById(id).isPresent()) {
            userrepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("User with id " + id + " Not Found!");
        }
    }
}
