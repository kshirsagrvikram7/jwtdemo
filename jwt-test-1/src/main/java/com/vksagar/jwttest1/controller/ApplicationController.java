package com.vksagar.jwttest1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
public class ApplicationController {

	@GetMapping("/welcome")
	public String verifyApplicationAccess() {
		return "Welcome, you are secured";
	}
}
