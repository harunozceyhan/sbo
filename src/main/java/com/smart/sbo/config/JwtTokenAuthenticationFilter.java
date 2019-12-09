package com.smart.sbo.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class JwtTokenAuthenticationFilter extends  OncePerRequestFilter {
    
	private String signingKey;
	
	public JwtTokenAuthenticationFilter(String signingKey) {
		this.signingKey = signingKey;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		try {	// exceptions might be thrown in creating the claims if for example the token is expired
			if (!request.getRequestURI().equals("/actuator/health")) {
				Jws<Claims> claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(request.getHeader("Authorization").replace("Bearer ", ""));
				claims.getBody();
			}
		} catch (Exception e) {
			// In case of failure. Make sure it's clear; so guarantee user won't be authenticated
			e.printStackTrace();
			SecurityContextHolder.clearContext();
		}
		// go to the next filter in the filter chain
		chain.doFilter(request, response);
	}

}