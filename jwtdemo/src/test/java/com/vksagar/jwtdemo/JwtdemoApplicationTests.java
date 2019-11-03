package com.vksagar.jwtdemo;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vksagar.jwtdemo.model.User;
import com.vksagar.jwtdemo.repository.UserRepository;

@SpringBootTest
class JwtdemoApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	void fetchUsernameTest() {
		String username="thomaskz";
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User not found with username or email ->" + username));
		System.out.println("User: "+user);
		System.out.println("Roles:"+user.getRoles());
		
	}

}
