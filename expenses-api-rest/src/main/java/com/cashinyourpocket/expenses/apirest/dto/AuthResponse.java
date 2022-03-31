package com.cashinyourpocket.expenses.apirest.dto;

import com.cashinyourpocket.expenses.application.user.model.JwtResponse;
import com.cashinyourpocket.expenses.application.user.model.UserData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthResponse {
  private UserData userData;
  private JwtResponse jwt;
}
