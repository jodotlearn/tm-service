package org.bianbian.tmservice.config;

import org.bianbian.tmservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		org.bianbian.tmservice.entity.User user = userRepository.findByAccount(username);
		if (user == null) {
			throw new UsernameNotFoundException(username + " not found");
		}
		return User.builder()
				.username(user.getAccount())
				.password(user.getPassword())
				.roles(user.getMainRole())
				.build();
	}

}
