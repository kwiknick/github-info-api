package io.willard.github_info_api.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GithubUserRepos {
    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    @JsonAlias("html_url")
    private String url;
}
