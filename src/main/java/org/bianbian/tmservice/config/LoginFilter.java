package org.bianbian.tmservice.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bianbian.tmservice.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginFilter extends AbstractAuthenticationProcessingFilter{

	private static final String TOKEN_PREFIX = "Bearer";
	
	public LoginFilter(String url, AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
	    String password = request.getParameter("password");
	
	    if (username == null) {
	        username = "";
	    }
	
	    if (password == null) {
	        password = "";
	    }
	
	    username = username.trim();
	    // 觸發AuthenticationManager
	    return getAuthenticationManager().authenticate(
	            new UsernamePasswordAuthenticationToken(
	            		username, password
	            )
	    );
	}
	
	@Override
	protected void successfulAuthentication(
			HttpServletRequest request
			, HttpServletResponse response
			, FilterChain chain
			, Authentication authResult) throws IOException, ServletException {
		
		String jws =  JwtUtils.addAuthentication(authResult);
        response.addHeader("Authorization", TOKEN_PREFIX + " " + jws);  
        response.addHeader("Access-Control-Expose-Headers", "Authorization");  
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getOutputStream().println("login failed.");
	}

}
