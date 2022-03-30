package com.cashinyourpocket.expenses.apirest.controller;

import java.util.Optional;

import com.cashinyourpocket.expenses.apirest.dto.UserSecurityDto;
import com.cashinyourpocket.expenses.apirest.mapper.UserMapper;
import com.cashinyourpocket.expenses.application.service.UserService;
import com.cashinyourpocket.expenses.application.user.JwtRequestFilter;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

  private static final Logger LOGGER = LogManager.getLogger(UserController.class);

  private final UserService usuariosService;
  private final JwtRequestFilter jwtRequestFilter;

  @RequestMapping(value = "/user", method = RequestMethod.GET)
  public ResponseEntity<UserSecurityDto> getUser(HttpServletRequest request) {
    final String user = jwtRequestFilter.getUser(request);
    return ResponseEntity.of(Optional.of(UserMapper.toUserSecurityDto(usuariosService.getUser(user))));
  }
}
