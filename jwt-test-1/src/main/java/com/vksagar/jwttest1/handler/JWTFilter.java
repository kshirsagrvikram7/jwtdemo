package com.vksagar.jwttest1.handler;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

@Component
public class JWTFilter extends GenericFilterBean {
	public static final String SECRET_KEY = "abcd@secret";

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("In doFilter--->");
		HttpServletRequest request = (HttpServletRequest) req;
		String authorizatin = request.getHeader("authorization");
		System.out.println("Authorization : "+authorizatin);
		if (authorizatin == null || !authorizatin.startsWith("Bearer "))
			throw new ServletException("401-Unauthorized");

		try {
			Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authorizatin.substring(7)).getBody();
			request.setAttribute("claims", claims);
		} catch (SignatureException e) {
			throw new ServletException("401-Unauthorized");
		}
		chain.doFilter(req, res);
	}

}
