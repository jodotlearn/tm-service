package org.jomurmur.tmservice.service.impl;

import java.util.List;

import org.jomurmur.tmservice.dto.UserDTO;
import org.jomurmur.tmservice.entity.User;
import org.jomurmur.tmservice.repository.UserRepository;
import org.jomurmur.tmservice.service.UserService;
import org.jomurmur.tmservice.utils.ConverterUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public String createUser(UserDTO u) {
    	System.out.println(u);
//    	User user = mapper.map(u, User.class);
    	User user = ConverterUtils.map(u, User.class);
    	System.out.println(user);
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
