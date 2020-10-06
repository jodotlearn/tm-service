package org.jomurmur.tmservice.service;


import org.jomurmur.tmservice.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public String createUser(User u);
    public List<User> findAll();
}
