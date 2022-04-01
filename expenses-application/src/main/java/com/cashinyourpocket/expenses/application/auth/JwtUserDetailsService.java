package com.cashinyourpocket.expenses.application.auth;

import java.util.ArrayList;
import java.util.List;

import com.cashinyourpocket.expenses.application.mapper.UserDataMapper;
import com.cashinyourpocket.expenses.application.user.model.UserData;
import com.cashinyourpocket.expenses.data.model.UserJpa;
import com.cashinyourpocket.expenses.data.repository.UserRepository;
import com.cashinyourpocket.expenses.expections.CustomException;
import com.cashinyourpocket.expenses.expections.ErrorEnum;
import com.cashinyourpocket.expenses.model.AddUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		List<UserJpa> userList = userRepository.findByUsername(username);
		if(!userList.isEmpty()) {
			User user = UserDataMapper.toUser(userList.get(0));
			return user;
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

	}

}