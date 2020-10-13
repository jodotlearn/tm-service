package org.bianbian.tmservice.utils;

import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class JwtUtils {
	private static final String TOKEN_PREFIX = "Bearer";
	private static final String HEADER_STRING = "Authorization";
	private static final Key key = MacProvider.generateKey();
	private static final long EXPIRATIONTIME = 28800_000;
	
	private JwtUtils() {}
	
	public static String addAuthentication(Authentication authResult) {
    	return Jwts.builder()
    			// 在Payload放入自定義的聲明方法如下
//    			.claim("email","aaa")
    			// 在Payload放入sub保留聲明
    			.setSubject(authResult.getName())
    			// 在Payload放入exp保留聲明
    			.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
    			.signWith(key).compact();
	}
	
	public static Authentication getAuthentication(String token) {
		Claims claims = Jwts.parserBuilder()
							.setSigningKey(key)
							.build()
							.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
							.getBody();
		String account = claims.getSubject();
		List<GrantedAuthority> authorities = 
				AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorize"));
        // 返回Token
        return account != null ?
				new UsernamePasswordAuthenticationToken(account, null, authorities) :
                null;
	}
}
