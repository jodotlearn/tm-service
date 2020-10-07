package org.jomurmur.tmservice.service;


import java.util.List;

import org.jomurmur.tmservice.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public String createUser(UserDTO u);
    public List<UserDTO> findAll();
}
