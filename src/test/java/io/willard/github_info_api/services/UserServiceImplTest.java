package io.willard.github_info_api.services;

import io.willard.github_info_api.models.GithubUser;
import io.willard.github_info_api.models.GithubUserRepos;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceImplTest {
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ResponseEntity<GithubUser> userResponseEntity;
    @Mock
    private ResponseEntity<GithubUserRepos> userRepoResponseEntity;
    @InjectMocks
    UserServiceImpl userService;

    @Test
    void getUserInfoTest_HappyPath() {
        when(restTemplate.exchange(anyString(), Mockito.eq(HttpMethod.GET),
                Mockito.any(), Mockito.<ParameterizedTypeReference<GithubUser>>any())).thenReturn(userResponseEntity);
        GithubUser user = userService.getUserInfo("octocat");
        verify(restTemplate, Mockito.times(1)).exchange(anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(), Mockito.<ParameterizedTypeReference<GithubUser>>any());
    }

    @Test
    void getUserRepoInfoTest_HappyPath() {
        when(restTemplate.exchange(anyString(), Mockito.eq(HttpMethod.GET),
                Mockito.any(), Mockito.<ParameterizedTypeReference<GithubUserRepos>>any())).thenReturn(userRepoResponseEntity);
        List<GithubUserRepos> userRepos = userService.getUserRepoInfo("octocat");
        verify(restTemplate, Mockito.times(1)).exchange(anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(), Mockito.<ParameterizedTypeReference<GithubUserRepos>>any());
    }
}
