package com.jeev.login_application.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jeev.login_application.repository.IUserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final IUserRepository userRepository;

	/**
	 * @return {@link UserDetailsService}
	 * 
	 *         <p>
	 *         The method uses findByUserEmail() method of {@link UserRepo} to get
	 *         the User Credentials from the Database.
	 */

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}
}