package com.cashinyourpocket.expenses.apirest.mapper;

import com.cashinyourpocket.expenses.apirest.dto.UserSecurityDto;
import com.cashinyourpocket.expenses.data.model.UserSecurity;

public class UserMapper {

  public static UserSecurityDto toUserSecurityDto(UserSecurity model){
    return UserSecurityDto.builder().user(model.getUser()).rol(model.getRol()).build();
  }

}
