package com.cashinyourpocket.expenses.application.service;

import com.cashinyourpocket.expenses.application.user.model.UserData;
import com.cashinyourpocket.expenses.data.model.UserJpa;

public interface UserService {

  UserJpa getUser(String user);
  UserData loginUser(String user, String password);


}
