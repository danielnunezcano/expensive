package com.cashinyourpocket.expenses.apirest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cashinyourpocket.expenses.application.service.UserService;
import com.cashinyourpocket.expenses.application.user.JwtRequestFilter;
import com.cashinyourpocket.expenses.data.model.UserJpa;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class UserControllerTest {

  @Mock
  private HttpServletRequest httpServletRequest;

  private static final String USER = "user";
  private static final Integer ROL = 1;

  private final UserService usuariosService = mock(UserService.class);
  private final JwtRequestFilter jwtRequestFilter = mock(JwtRequestFilter.class);

  private final UserController sut = new UserController(usuariosService, jwtRequestFilter);

  @Test
  public void getUserOK() {
    UserJpa userSecurity = UserJpa.builder().username(USER).role(ROL).build();

    when(jwtRequestFilter.getUser(any())).thenReturn(USER);
    when(usuariosService.getUser(any())).thenReturn(userSecurity);

    sut.getUser(httpServletRequest);

    verify(usuariosService).getUser(USER);
    sut.getUser(httpServletRequest).getBody().equals(userSecurity);

  }

}
