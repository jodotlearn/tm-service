package org.bianbian.tmservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
	@Autowired
	private TmAuthenticationProvider tmAuthenticationProvider;
	@Autowired
	private UserLoginService userLoginService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		// 對請求進行驗證
		.authorizeRequests()
		// 所有/login的请求放行
		.antMatchers("/login").permitAll()
		.anyRequest().authenticated()
		.and()
		// /login 的請求，交給 LoginFilter 處理
		.addFilterBefore(new LoginFilter("/login", authenticationManager())
				, UsernamePasswordAuthenticationFilter.class)
		// 其他請求交給 JWTAuthenticationFilter 處理
		.addFilterBefore(new JWTAuthenticationFilter()
				, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(tmAuthenticationProvider)
			.userDetailsService(userLoginService)
			.passwordEncoder(passwordEncoder());
	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
