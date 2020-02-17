package com.clinics.auth.security;

import com.clinics.auth.model.UserDAO;
import com.clinics.common.security.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Collections;
import java.util.Date;


public interface JwtMaker extends JwtProperties {

	default String makeJwtToken(UserDAO userDAO) {
		return Jwts.builder()
				.setSubject(userDAO.getEmail())
				.claim("authorities", Collections.singletonList("ROLE_" + userDAO.getRole()))
				.claim("UUID", userDAO.getUuid())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
				.signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
				.compact();
	}
}