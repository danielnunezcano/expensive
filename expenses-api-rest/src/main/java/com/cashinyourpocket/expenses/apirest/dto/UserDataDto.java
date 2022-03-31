package com.cashinyourpocket.expenses.apirest.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDataDto {

  private String username;
  private String name;
  private String surname;
  private Integer role;

}
