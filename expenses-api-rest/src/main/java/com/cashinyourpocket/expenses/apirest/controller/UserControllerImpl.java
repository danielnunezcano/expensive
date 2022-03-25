package com.cashinyourpocket.expenses.apirest.controller;

import java.util.Optional;

import com.cashinyourpocket.expenses.apirest.dto.UserSecurityDto;
import com.cashinyourpocket.expenses.apirest.mapper.UserMapper;
import com.cashinyourpocket.expenses.application.service.UsuariosService;
import com.cashinyourpocket.expenses.application.user.JwtRequestFilter;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

  private final UsuariosService usuariosService;
  private final JwtRequestFilter jwtRequestFilter;

  public ResponseEntity<UserSecurityDto> getUser(HttpServletRequest request) {
    final String user = jwtRequestFilter.getUser(request);
    return ResponseEntity.of(Optional.of(UserMapper.toUserSecurityDto(usuariosService.getUser(user))));
  }

}
