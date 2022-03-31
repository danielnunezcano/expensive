package com.cashinyourpocket.expenses.application.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserData {

  private String username;
  private String name;
  private String surname;
  private Integer role;

}
