package org.jomurmur.tmservice.controller;

import org.jomurmur.tmservice.entity.User;
import org.jomurmur.tmservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value="/add")
    public String createUser(@RequestBody User u){
        String id = userService.createUser(u);
        return id;
    }

    @GetMapping(value="/findAll")
    public List<User> getAllUser(){
        return userService.findAll();
    }
}
