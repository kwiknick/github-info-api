package io.willard.github_info_api.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GithubCombinedUserInfo {
    private String user_name;
    private String display_name;
    private String avatar;
    private String geo_location;
    private String email;
    private String url;
    private LocalDateTime created_at;
    private List<GithubUserRepos> repos;

    public GithubCombinedUserInfo(GithubUser githubUser, List<GithubUserRepos> githubUserReposList) {
        this.user_name = githubUser.getUser_name();
        this.display_name = githubUser.getDisplay_name();
        this.avatar = githubUser.getAvatar();
        this.geo_location = githubUser.getGeo_location();
        this.email = githubUser.getEmail();
        this.url = githubUser.getUrl();
        this.created_at = githubUser.getCreated_at();
        this.repos = githubUserReposList;
    }
}
