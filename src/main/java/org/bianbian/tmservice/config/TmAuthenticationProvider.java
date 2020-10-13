package org.bianbian.tmservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TmAuthenticationProvider implements AuthenticationProvider {	
	@Autowired
	private UserLoginService userLoginService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String account = authentication.getName();
		String password = authentication.getCredentials().toString();
		UserDetails userDetails = userLoginService.loadUserByUsername(account);
		if (account.equals(userDetails.getUsername()) && password.equals(userDetails.getPassword())) {
			return new UsernamePasswordAuthenticationToken(account, password);
		} else {
			throw new BadCredentialsException("Account or password error.");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
