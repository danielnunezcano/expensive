package com.cashinyourpocket.expenses.apirest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cashinyourpocket.expenses.apirest.dto.AddUserRequestDto;
import com.cashinyourpocket.expenses.apirest.dto.UserDataDto;
import com.cashinyourpocket.expenses.apirest.dto.UserSecurityDto;
import com.cashinyourpocket.expenses.application.service.UserService;
import com.cashinyourpocket.expenses.application.user.JwtRequestFilter;
import com.cashinyourpocket.expenses.application.user.model.UserData;
import com.cashinyourpocket.expenses.data.model.UserJpa;

import javax.servlet.http.HttpServletRequest;

import com.cashinyourpocket.expenses.model.AddUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

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

    @Test
    public void addUserOK() {

        AddUserRequestDto addUserRequestDto = AddUserRequestDto.builder().build();
        UserData userData = UserData.builder()
                .username("daniel@gmail.com")
                .name("Daniel")
                .surname("Núñez")
                .role(1)
                .build();

        when(usuariosService.addUser(any())).thenReturn(userData);

        ResponseEntity<UserDataDto> responseEntity = sut.addUser(addUserRequestDto);
        UserDataDto response = responseEntity.getBody();
        Assertions.assertEquals(userData.getUsername(),response.getUsername());
        Assertions.assertEquals(userData.getName(),response.getName());
        Assertions.assertEquals(userData.getSurname(),response.getSurname());
        Assertions.assertEquals(userData.getRole(),response.getRole());

    }

}
