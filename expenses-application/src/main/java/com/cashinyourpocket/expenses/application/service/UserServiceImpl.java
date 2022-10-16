package com.cashinyourpocket.expenses.application.service;

import java.util.List;

import com.cashinyourpocket.expenses.application.mapper.UserDataMapper;
import com.cashinyourpocket.expenses.application.user.model.UserData;
import com.cashinyourpocket.expenses.data.model.UserJpa;
import com.cashinyourpocket.expenses.data.repository.UserRepository;
import com.cashinyourpocket.expenses.expections.CustomException;
import com.cashinyourpocket.expenses.expections.ErrorEnum;
import com.cashinyourpocket.expenses.model.AddUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  @Override
  public UserJpa getUser(String user) {
    List<UserJpa> userList = userRepository.findByUsername(user);
    if(!userList.isEmpty()) {
      return userList.get(0);
    } else {
      throw new CustomException(ErrorEnum.FILE_NOT_EXIST);
    }
  }

  @Override
  public UserData addUser(final AddUserRequest addUserRequest) {

    UserJpa userJpa = UserDataMapper.addUserRequestToUserJpa(addUserRequest);
    return UserDataMapper.userDetailstoUserData(userRepository.save(userJpa));
  }

  @Override
  public UserData getUserData(String user) {
    List<UserJpa> userList = userRepository.findByUsername(user);
    if(!userList.isEmpty()) {
      return UserDataMapper.userDetailstoUserData(userList.get(0));
    } else {
      throw new UsernameNotFoundException("User not found with username: " + user);
    }
  }

}
