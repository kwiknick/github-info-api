package io.willard.github_info_api.services;

import io.willard.github_info_api.models.GithubUser;
import io.willard.github_info_api.models.GithubUserRepos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LogManager.getLogger();
    private final RestTemplate restTemplate;

    public static final String USER_API_BASE_URL = "https://api.github.com/users/";

    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GithubUser getUserInfo(String username) {
        try {
            log.debug("Sending a request to the Github API to retrieve User Info");
            ResponseEntity<GithubUser> response = restTemplate.exchange(USER_API_BASE_URL + username,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {});

            logRateLimitHeaderValues(response.getHeaders());

            return response.getBody();

        } catch (Exception ex) {
            log.error("An Exception was thrown while attempting to get User Info with provided username: " + username, ex);
            throw ex;
        }
    }

    public List<GithubUserRepos> getUserRepoInfo(String username) {
        try {
            log.debug("Sending a request to the Github API to retrieve User Repository Info");
            ResponseEntity<List<GithubUserRepos>> response = restTemplate.exchange(USER_API_BASE_URL + username + "/repos",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {});

            logRateLimitHeaderValues(response.getHeaders());

            return response.getBody();

        } catch (Exception ex) {
            log.error("An Exception was thrown while attempting to get User Repository Info with provided username: " + username, ex);
            throw ex;
        }
    }

    private static void logRateLimitHeaderValues(HttpHeaders headers) {
        List<String> headerKeysToLog = List.of("x-ratelimit-remaining", "x-ratelimit-reset", "retry-after");
        for (String headerKey : headerKeysToLog) {
            try {
                log.info(headerKey + " header value is: " + headers.get(headerKey));
            } catch (Exception ex) {
                log.debug(headerKey + " does not currently exist in the response. Nothing to log");
            }
        }
    }
}
