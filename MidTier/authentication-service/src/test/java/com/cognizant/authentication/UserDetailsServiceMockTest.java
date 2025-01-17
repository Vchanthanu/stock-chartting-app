package com.cognizant.authentication;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

//import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cognizant.authentication.model.Role;
import com.cognizant.authentication.model.User;
import com.cognizant.authentication.repository.UserRepository;
import com.cognizant.authentication.service.AppUserDetailsService;

/**
 * @author 805831
 *
 */
//@SpringBootTest
public class UserDetailsServiceMockTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceApplicationTests.class);

//	@Test
	public void mockTestLoadUserByUsername() {
		UserRepository repository = Mockito.mock(UserRepository.class);
		when(repository.findByUserNameAndConfirmed("1",true)).thenReturn(createUser());
		UserDetailsService service = new AppUserDetailsService(repository);
		UserDetails user = service.loadUserByUsername("1");
		String expected = "$2a$10$R/lZJuT9skteNmAku9Y7aeutxbOKstD5xE5bHOf74M2PHZipyt3yK";
//		assertEquals(expected, user.getPassword());
	}

//	@Test
	public void mockTestLoadByUserNameWithUserNull() {
		UserRepository repository = Mockito.mock(UserRepository.class);
		when(repository.findByUserNameAndConfirmed("user",true)).thenReturn(null);
		UserDetailsService service = new AppUserDetailsService(repository);
		try {
			UserDetails user = service.loadUserByUsername("usr1");
			LOGGER.debug("user:{}", user);
		} catch (UsernameNotFoundException e) {
			LOGGER.error("User not found", e);
//			assertTrue(true);
			return;
		}
//		assertFalse(true);
	}

	private User createUser() {
		User user = new User();
		Role role =new Role();
		role.setId(1);
		role.setType("ADMIN");
		user.setRole(role);
		user.setUserName("1");
		user.setPassword("$2a$10$R/lZJuT9skteNmAku9Y7aeutxbOKstD5xE5bHOf74M2PHZipyt3yK");
		user.setConfirmed(true);
		user.setEmail("admin@gmail.com");
		user.setMobileNumber("7894561230");
		user.setId(1);
		return user;
	}
}
