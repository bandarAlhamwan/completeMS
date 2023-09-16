package com.gateway.APIGatewayService.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {

	
	// https://allkeysgenerator.com/random/security-encryption-key-generator.aspx
    public static final String SECRETS = "357538782F4125442A472D4B6150645367566B59703373367639792442264528";

    
    public void validateToken(final String token) {
    	Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

	private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRETS);
        return Keys.hmacShaKeyFor(keyBytes);
    }
	
	public String getUsernameFromToken(String token) {
	    String username;
	    try {
	        final Claims claims = getClaimsFromToken(token);
	        username = claims.getSubject();
	    } catch (Exception e) {
	        username = null;
	    }
	    return username;
	}
	
	private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRETS)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
}
