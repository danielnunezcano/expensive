package com.cashinyourpocket.expenses.application.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cashinyourpocket.expenses.application.user.JwtRequestFilter;
import com.cashinyourpocket.expenses.data.model.UserSecurity;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class UserServiceTest {

  @Mock
  private HttpServletRequest httpServletRequest;

  private static final String USER = "user";
  private static final String ROL = "USER";

  private final UsuariosService sut = new UsuariosServiceImpl();

  @Test
  public void getUserOK() {
    UserSecurity userSecurity = UserSecurity.builder().user(USER).rol(ROL).build();

    sut.getUser(USER);

    sut.getUser(USER).equals(userSecurity);

  }

}
