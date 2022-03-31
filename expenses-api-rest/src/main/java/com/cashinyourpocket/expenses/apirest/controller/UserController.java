package com.cashinyourpocket.expenses.apirest.controller;

import java.util.Optional;

import com.cashinyourpocket.expenses.apirest.dto.UserDataDto;
import com.cashinyourpocket.expenses.apirest.dto.UserSecurityDto;
import com.cashinyourpocket.expenses.apirest.mapper.UserMapper;
import com.cashinyourpocket.expenses.application.service.UserService;
import com.cashinyourpocket.expenses.application.user.JwtRequestFilter;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

  private final UserService usuariosService;
  private final JwtRequestFilter jwtRequestFilter;

  @RequestMapping(value = "/user", method = RequestMethod.GET)
  public ResponseEntity<UserSecurityDto> getUser(HttpServletRequest request) {
    final String user = jwtRequestFilter.getUser(request);
    return ResponseEntity.of(Optional.of(UserMapper.toUserSecurityDto(usuariosService.getUser(user))));
  }

  @RequestMapping(value = "/user", method = RequestMethod.POST)
  public ResponseEntity<UserDataDto> addUser() {

    return ResponseEntity.of(Optional.of(UserDataDto.builder().build()));
  }
}
