package org.jomurmur.tmservice.service.impl;

import org.jomurmur.tmservice.entity.User;
import org.jomurmur.tmservice.repository.UserRepository;
import org.jomurmur.tmservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String createUser(User u) {
        userRepository.save(u);
        return u.getId();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
