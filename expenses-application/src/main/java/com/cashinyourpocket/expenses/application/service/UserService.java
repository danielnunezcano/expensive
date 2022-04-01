package com.cashinyourpocket.expenses.application.service;

import com.cashinyourpocket.expenses.application.user.model.UserData;
import com.cashinyourpocket.expenses.data.model.UserJpa;
import com.cashinyourpocket.expenses.model.AddUserRequest;

public interface UserService {

  UserJpa getUser(String user);
  UserData addUser(AddUserRequest addUserRequest);
  UserData getUserData(String user);


}
