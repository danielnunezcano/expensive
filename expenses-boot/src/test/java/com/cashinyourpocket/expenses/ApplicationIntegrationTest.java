package com.cashinyourpocket.expenses;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.cashinyourpocket.expenses.apirest.dto.AddUserRequestDto;
import com.cashinyourpocket.expenses.application.user.model.JwtRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicationIntegrationTest {

  private static final String USERNAME = "daniel@gmail.com";
  private static final String PASSWORD = "password";
  private static final Integer ROL = 1;

  private final MockMvc mockMvc;

  private final ObjectMapper objectMapper;

  @Test
  public void shouldGetUser() throws Exception {
    final ResponseTokenTest responseTokenTest = authentication(USERNAME,PASSWORD);
    String basicAuthorization = "Bearer ".concat(responseTokenTest.getToken());
    mockMvc.perform(get("/user").header("Authorization", basicAuthorization)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.user").value(USERNAME))
        .andExpect(jsonPath("$.rol").value(ROL));
  }

  @Test
  public void shouldUnauthorizedIfNotToken() throws Exception {
    mockMvc.perform(get("/user")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void shouldErrorWhenUserNotExist() throws Exception {
    final String jsonContent = objectMapper.writeValueAsString(new JwtRequest("user", PASSWORD));
    mockMvc.perform(post("/authenticate")
            .content(jsonContent)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void shouldErrorWhenPasswordIsWrong() throws Exception {
    final String jsonContent = objectMapper.writeValueAsString(new JwtRequest(USERNAME, "pass"));
    mockMvc.perform(post("/authenticate")
            .content(jsonContent)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized());
  }

  private ResponseTokenTest authentication(String user, String password) throws Exception{
    final String jsonContent = objectMapper.writeValueAsString(new JwtRequest(user, password));
    MvcResult result = mockMvc.perform(post("/authenticate")
            .content(jsonContent)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
    String content = result.getResponse().getContentAsString();
    return ResponseTokenTest.builder().token(content.split("\"")[content.split("\"").length-2]).build();
  }

  @Test
  public void shouldAddUser() throws Exception {

    AddUserRequestDto request = AddUserRequestDto.builder()
            .username("julian@gmail.com")
            .name("Julian")
            .surname("Fernandez")
            .password("asdfgqwert")
            .role(1)
            .build();

    final String jsonContent = objectMapper.writeValueAsString(request);
    mockMvc.perform(post("/addUser")
                    .content(jsonContent)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.username").value(request.getUsername()))
            .andExpect(jsonPath("$.name").value(request.getName()))
            .andExpect(jsonPath("$.surname").value(request.getSurname()))
            .andExpect(jsonPath("$.role").value(request.getRole()));
    authentication(request.getUsername(), request.getPassword());
  }


}