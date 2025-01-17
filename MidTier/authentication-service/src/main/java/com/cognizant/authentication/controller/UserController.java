package com.cognizant.authentication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.authentication.exception.UserAlreadyExistsException;
import com.cognizant.authentication.model.User;
import com.cognizant.authentication.service.AppUserDetailsService;

/**
 * @author 805831
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private AppUserDetailsService appUserDetailsService;

	@PostMapping
	private void addUser(@RequestBody @Valid User user) throws UserAlreadyExistsException {
		appUserDetailsService.addUser(user);
	}

	@GetMapping("/{username}")
	public User findByUserName(@PathVariable String username) {
		return appUserDetailsService.findByUserName(username);
	}

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@PutMapping("/{username}")
	public void modifyUser(@PathVariable String username,@RequestBody @Valid User user) {
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		appUserDetailsService.modifyUser(user);
	}
}
