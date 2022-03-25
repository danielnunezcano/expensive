package com.cashinyourpocket.expenses.apirest.controller;

import com.cashinyourpocket.expenses.apirest.dto.UserSecurityDto;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface UserController {

  @GetMapping({"/user"})
  default ResponseEntity<UserSecurityDto> getUser(HttpServletRequest request) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @GetMapping({"/home"})
  default ResponseEntity<String> home() {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @GetMapping({"/"})
  default ResponseEntity<String> initial() {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @GetMapping({"/hello"})
  default ResponseEntity<String> hello() {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @GetMapping({"/login"})
  default ResponseEntity<String> login() {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
