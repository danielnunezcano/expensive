package com.cashinyourpocket.expenses.application.auth;

import java.util.ArrayList;
import java.util.List;

import com.cashinyourpocket.expenses.data.model.UserJpa;
import com.cashinyourpocket.expenses.data.repository.UserRepository;
import com.cashinyourpocket.expenses.expections.CustomException;
import com.cashinyourpocket.expenses.expections.ErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if ("daniel@gmail.com".equals(username)) {
			return new User("daniel@gmail.com", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
					new ArrayList<>());
		} else if ("juan@gmail.com".equals(username)) {
			return new User("juan@gmail.com", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}