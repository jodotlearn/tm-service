package org.bianbian.tmservice.controller;

import java.util.List;

import org.bianbian.tmservice.dto.UserDTO;
import org.bianbian.tmservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping(value = "/add")
	public String createUser(@RequestBody UserDTO u) {
		String id = userService.createUser(u);
		return id;
	}

	@GetMapping(value = "/findAll")
	public List<UserDTO> getAllUser() {
		return userService.findAll();
	}
}
