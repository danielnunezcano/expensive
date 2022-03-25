package com.cashinyourpocket.expenses.application.service;

import com.cashinyourpocket.expenses.data.model.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuariosServiceImpl implements UsuariosService {

  @Override
  public UserSecurity getUser(String user) {
    return UserSecurity.builder().user(user).rol("USER").build();
  }
}
