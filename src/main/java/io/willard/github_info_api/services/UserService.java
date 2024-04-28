package io.willard.github_info_api.services;

import io.willard.github_info_api.models.GithubUser;
import io.willard.github_info_api.models.GithubUserRepos;

import java.util.List;

public interface UserService {
    GithubUser getUserInfo(String username);
    List<GithubUserRepos> getUserRepoInfo(String username);
}
