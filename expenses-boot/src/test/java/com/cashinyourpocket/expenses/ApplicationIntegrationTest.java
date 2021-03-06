package com.cashinyourpocket.expenses;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.cashinyourpocket.expenses.application.user.model.JwtRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
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

  private static final String USER = "daniel";
  private static final String PASSWORD = "password";
  private static final String ROL = "USER";

  private final MockMvc mockMvc;

  private final ObjectMapper objectMapper;

  @Test
  public void shouldGetUser() throws Exception {
    final ResponseTokenTest responseTokenTest = authentication(USER,PASSWORD);
    String basicAuthorization = "Bearer ".concat(responseTokenTest.getToken());
    mockMvc.perform(get("/user").header("Authorization", basicAuthorization)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.user").value(USER))
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
    final String jsonContent = objectMapper.writeValueAsString(new JwtRequest(USER, "pass"));
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
    return new Gson().fromJson(content, ResponseTokenTest.class);
  }


}