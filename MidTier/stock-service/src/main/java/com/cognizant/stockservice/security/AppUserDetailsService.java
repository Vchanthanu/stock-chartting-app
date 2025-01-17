package com.cognizant.stockservice.security;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.stockservice.bean.Role;
import com.cognizant.stockservice.bean.User;
import com.cognizant.stockservice.exception.UserAlreadyExistsException;
import com.cognizant.stockservice.repository.RoleRepository;
import com.cognizant.stockservice.repository.UserRepository;

/**
 * @author 805831
 *
 */
@Service
public class AppUserDetailsService implements UserDetailsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppUserDetailsService.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	public AppUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByUserNameAndConfirmed(userName, true);
		LOGGER.info("Inside Service");
		if (user == null) {
			throw new UsernameNotFoundException("Username is not found");
		} else {
			return new AppUser(user);
		}

	}

	public void addUser(@Valid User user) throws UserAlreadyExistsException {
		User users = userRepository.findByUserName(user.getUserName());
		if (users == null) {
			user.setPassword(passwordEncoder().encode(user.getPassword()));
			Role role = roleRepository.findById(2).get();
			user.setRole(role);
			user.setConfirmed(true);
			userRepository.save(user);
		} else {
			throw new UserAlreadyExistsException("User Already Exists");
		}
	}

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
