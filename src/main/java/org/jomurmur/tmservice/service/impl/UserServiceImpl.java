package org.jomurmur.tmservice.service.impl;

import java.util.List;

import org.jomurmur.tmservice.dto.UserDTO;
import org.jomurmur.tmservice.entity.User;
import org.jomurmur.tmservice.repository.UserRepository;
import org.jomurmur.tmservice.service.UserService;
import org.jomurmur.tmservice.utils.ConverterUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String createUser(UserDTO u) {
    	User user = ConverterUtils.map(u, User.class);
    	user.setPassword(passwordEncoder.encode(u.getPassword()));
        userRepository.save(user);
        return u.getId();
    }

    @Override
    public List<UserDTO> findAll() {
    	List<User> users = userRepository.findAll();
    	List<UserDTO> userDtos = ConverterUtils.mapList(users, UserDTO.class);
        return userDtos;
    }
}
