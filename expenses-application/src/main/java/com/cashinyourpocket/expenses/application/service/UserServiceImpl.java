package com.cashinyourpocket.expenses.application.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.cashinyourpocket.expenses.application.mapper.UserDataMapper;
import com.cashinyourpocket.expenses.application.user.model.UserData;
import com.cashinyourpocket.expenses.data.model.UserJpa;
import com.cashinyourpocket.expenses.data.repository.UserRepository;
import com.cashinyourpocket.expenses.expections.CustomException;
import com.cashinyourpocket.expenses.expections.ErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

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
  public UserData loginUser(String user, String password) {
    List<UserJpa> userList = userRepository.findByUsername(user);
    String encriptPassword = convertSHA256(password);
    if(!userList.isEmpty() && encriptPassword.equals(userList.get(0).getPassword())) {
      return UserDataMapper.userDetailstoUserData(userList.get(0));
    } else {
      throw new UsernameNotFoundException("User not found with username: " + user);
    }
  }

  private String convertSHA256(String password) {
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("SHA-256");
    }
    catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }

    byte[] hash = md.digest(password.getBytes());
    StringBuffer sb = new StringBuffer();

    for(byte b : hash) {
      sb.append(String.format("%02x", b));
    }

    return sb.toString();
  }

}
