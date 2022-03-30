package com.cashinyourpocket.expenses.apirest.auth;

import java.util.Optional;

import com.cashinyourpocket.expenses.apirest.dto.AuthResponse;
import com.cashinyourpocket.expenses.apirest.mapper.UserMapper;
import com.cashinyourpocket.expenses.application.auth.JwtUserDetailsService;
import com.cashinyourpocket.expenses.application.service.UserService;
import com.cashinyourpocket.expenses.application.user.JwtTokenUtil;
import com.cashinyourpocket.expenses.application.user.model.JwtRequest;
import com.cashinyourpocket.expenses.application.user.model.JwtResponse;
import com.cashinyourpocket.expenses.application.user.model.UserData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class JwtAuthenticationController {

  private final UserService usuariosService;

  private final AuthenticationManager authenticationManager;

  private final JwtTokenUtil jwtTokenUtil;

  private final JwtUserDetailsService userDetailsService;

  private final UserService userService;

  @CheckLogin
  @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

    authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

    UserData userData = userService.loginUser(authenticationRequest.getUsername(), authenticationRequest.getPassword());

//    final UserData userData2 = UserMapper.userDetailstoUserData((UserDetails) userDetailsService
//        .loadUserByUsername(authenticationRequest.getUsername()));

    final String token = jwtTokenUtil.generateToken(userData);
    //ResponseEntity.of(Optional.of(UserMapper.toUserSecurityDto(usuariosService.getUser(userData.getUsername()))));

    final AuthResponse response = AuthResponse.builder().userData(userData).jwt(new JwtResponse(token)).build();

    return ResponseEntity.ok(response);
  }

  private void authenticate(String email, String password) throws Exception {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    } catch (DisabledException e) {
      throw new Exception("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new Exception("INVALID_CREDENTIALS", e);
    }
  }
}