package com.cashinyourpocket.expenses.apirest.mapper;

import com.cashinyourpocket.expenses.apirest.dto.UserSecurityDto;
import com.cashinyourpocket.expenses.application.user.model.UserData;
import com.cashinyourpocket.expenses.data.model.UserJpa;
import org.springframework.security.core.userdetails.UserDetails;

public class UserMapper {

  public static UserSecurityDto toUserSecurityDto(UserJpa model){
    return UserSecurityDto.builder().user(model.getUsername()).rol(model.getRole().toString()).build();
  }

  public static UserData userDetailstoUserData(UserDetails userDetails){
    return UserData.builder().username(userDetails.getUsername()).name("Daniel").surname("Núñez").role(1).build();
  }

}
