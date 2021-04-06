package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.models.User;
import com.jeffreyorndorff.productivity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "securityUserService")
public class SecurityUserServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws EntityNotFoundException {
        User user = userRepository.findByUsernameIgnoringCase(s);
        if (user == null) {
            throw new EntityNotFoundException("Invalid username or password");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.getAuthority());
    }
}
