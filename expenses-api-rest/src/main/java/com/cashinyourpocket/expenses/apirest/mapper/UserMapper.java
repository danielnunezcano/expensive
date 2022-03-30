package com.cashinyourpocket.expenses.apirest.mapper;

import com.cashinyourpocket.expenses.apirest.dto.UserSecurityDto;
import com.cashinyourpocket.expenses.application.user.model.UserData;
import com.cashinyourpocket.expenses.data.model.UserSecurity;
import org.springframework.security.core.userdetails.UserDetails;

public class UserMapper {

  public static UserSecurityDto toUserSecurityDto(UserSecurity model){
    return UserSecurityDto.builder().user(model.getUser()).rol(model.getRol()).build();
  }

  public static UserData userDetailstoUserData(UserDetails userDetails){
    return UserData.builder().username(userDetails.getUsername()).name("Daniel").surname("Núñez").role("USER").build();
  }

}
