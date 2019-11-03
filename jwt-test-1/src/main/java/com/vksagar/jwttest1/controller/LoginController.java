package com.vksagar.jwttest1.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vksagar.jwttest1.handler.JWTFilter;
import com.vksagar.jwttest1.model.ApiToken;
import com.vksagar.jwttest1.model.Client;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
@RequestMapping("/login")
public class LoginController {

	@PostMapping
	public ResponseEntity<ApiToken> login(@RequestBody Client client) {
		
		Date iat = new Date();
		ApiToken apiToken = new ApiToken(Jwts.builder().setSubject(client.getClientName()).claim("roles", "user")
				.setIssuedAt(iat)
				.setExpiration(addMinutesToDate(1, iat))
				.signWith(SignatureAlgorithm.HS256, JWTFilter.SECRET_KEY)
				.compact());
		return new ResponseEntity<>(apiToken, HttpStatus.OK);
	}
	
	private static Date addMinutesToDate(int minutes, Date beforeTime){
	    final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs

	    long curTimeInMs = beforeTime.getTime();
	    Date afterAddingMins = new Date(curTimeInMs + (minutes * ONE_MINUTE_IN_MILLIS));
	    return afterAddingMins;
	}
}
