package com.cashinyourpocket.expenses.apirest.mapper;

import com.cashinyourpocket.expenses.apirest.dto.UserDataDto;
import com.cashinyourpocket.expenses.apirest.dto.UserSecurityDto;
import com.cashinyourpocket.expenses.application.user.model.UserData;
import com.cashinyourpocket.expenses.data.model.UserJpa;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserMapper {

  public static UserSecurityDto toUserSecurityDto(UserJpa model){
    return UserSecurityDto.builder().user(model.getUsername()).rol(model.getRole().toString()).build();
  }

  public static UserDataDto toUserDataDto (UserData userData){
    return UserDataDto.builder()
            .username(userData.getUsername())
            .name(userData.getName())
            .surname(userData.getSurname())
            .role(userData.getRole())
            .build();
  }

}
