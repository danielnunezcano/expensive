package com.cashinyourpocket.expenses.apirest.controller;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cashinyourpocket.expenses.apirest.auth.controller.UserController;
import com.cashinyourpocket.expenses.application.service.UsuariosService;
import com.cashinyourpocket.expenses.application.user.JwtRequestFilter;
import com.cashinyourpocket.expenses.data.model.UserSecurity;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class UserControllerTest {

  @Mock
  private HttpServletRequest httpServletRequest;

  private static final String USER = "user";
  private static final String ROL = "USER";

  private final UsuariosService usuariosService = mock(UsuariosService.class);
  private final JwtRequestFilter jwtRequestFilter = mock(JwtRequestFilter.class);

  private final UserController sut = new UserController(usuariosService, jwtRequestFilter);

  @Test
  public void getUserOK() {
    UserSecurity userSecurity = UserSecurity.builder().user(USER).rol(ROL).build();

    when(jwtRequestFilter.getUser(any())).thenReturn(USER);
    when(usuariosService.getUser(any())).thenReturn(userSecurity);

    sut.getUser(httpServletRequest);

    verify(usuariosService).getUser(USER);
    sut.getUser(httpServletRequest).getBody().equals(userSecurity);

  }

}
