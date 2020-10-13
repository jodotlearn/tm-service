package org.bianbian.tmservice.service;


import java.util.List;

import org.bianbian.tmservice.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public String createUser(UserDTO u);
    public List<UserDTO> findAll();
}
