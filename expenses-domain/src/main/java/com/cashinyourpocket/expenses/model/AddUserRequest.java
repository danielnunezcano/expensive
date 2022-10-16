package com.cashinyourpocket.expenses.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AddUserRequest {

    private String username;
    private String name;
    private String surname;
    private String password;
    private Integer role;

}