package com.cashinyourpocket.expenses.application.mapper;

import com.cashinyourpocket.expenses.application.user.model.UserData;
import com.cashinyourpocket.expenses.data.model.UserJpa;

public class UserDataMapper {

  public static UserData userDetailstoUserData(UserJpa userJpa){
    return UserData.builder()
        .username(userJpa.getUsername())
        .name(userJpa.getName())
        .surname(userJpa.getSurname())
        .role(userJpa.getRole())
        .build();
  }

}
