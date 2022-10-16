package com.cashinyourpocket.expenses.application.user.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserData {

  private String username;
  private String name;
  private String surname;
  private Integer role;

}
