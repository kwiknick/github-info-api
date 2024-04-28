package io.willard.github_info_api.controllers;

import io.willard.github_info_api.models.GithubUser;
import io.willard.github_info_api.services.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;

@WebMvcTest(UserInfoController.class)
public class UserInfoControllerTest {

    @MockBean
    private UserService userService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    @Test
    void shouldReturnUserInfo() throws Exception {
        Mockito.when(userService.getUserInfo(anyString())).thenReturn(new GithubUser("test_username",
                "Test Name", "Test Avatar", "St. Louis, MO", "test@email.com", "http://test.com", LocalDateTime.now()));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/user-info/octocat"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(8)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"octocat-", "-octocat", "octo--cat", "!octocat", "octo\"cat", "oct(ocat", "octocat%"})
    void shouldThrowWithBadUsername(String username) throws Exception {
        Mockito.when(userService.getUserInfo(anyString())).thenReturn(new GithubUser("test_username",
                "Test Name", "Test Avatar", "St. Louis, MO", "test@email.com", "http://test.com", LocalDateTime.now()));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/user-info/" + username))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
