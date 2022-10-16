package com.cashinyourpocket.expenses.application.mapper;

import com.cashinyourpocket.expenses.application.user.model.UserData;
import com.cashinyourpocket.expenses.data.model.UserJpa;
import com.cashinyourpocket.expenses.model.AddUserRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

public class UserDataMapper {

  public static User toUser(UserJpa userJpa){
    return new User(userJpa.getUsername(), userJpa.getPassword(),
            new ArrayList<>());
  }

  public static UserData userDetailstoUserData(UserJpa userJpa){
    return UserData.builder()
        .username(userJpa.getUsername())
        .name(userJpa.getName())
        .surname(userJpa.getSurname())
        .role(userJpa.getRole())
        .build();
  }

  public static UserJpa addUserRequestToUserJpa(AddUserRequest addUserRequest){
    return UserJpa.builder()
            .username(addUserRequest.getUsername())
            .name(addUserRequest.getName())
            .surname(addUserRequest.getSurname())
            .password(new BCryptPasswordEncoder().encode(addUserRequest.getPassword()))
            .role(addUserRequest.getRole())
            .build();
  }

}
