package com.cashinyourpocket.expenses.application.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.cashinyourpocket.expenses.data.model.UserJpa;
import com.cashinyourpocket.expenses.data.repository.UserRepository;
import com.cashinyourpocket.expenses.expections.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @InjectMocks
  private UserServiceImpl userService;

  @Mock
  private UserRepository userRepository;

  private static final String USER = "daniel@gmail.com";
  private static final Integer ROL = 1;

  @Test
  public void getUserOK() {
    List<UserJpa> userList = new ArrayList<>();
    userList.add(UserJpa.builder().id(1).username(USER).role(ROL).build());

    when(userRepository.findByUsername(any())).thenReturn(userList);

    UserJpa user = userService.getUser(USER);
    Assertions.assertEquals(USER,user.getUsername());

  }

  @Test
  public void userNotExist() {
    List<UserJpa> userList = new ArrayList<>();

    when(userRepository.findByUsername(any())).thenReturn(userList);

    try{
      userService.getUser(USER);
      Assertions.assertTrue(Boolean.FALSE);
    } catch (CustomException ex) {
      Assertions.assertTrue(Boolean.TRUE);
    }

  }

}
