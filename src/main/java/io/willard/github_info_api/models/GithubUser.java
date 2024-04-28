package io.willard.github_info_api.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class GithubUser {
    @JsonProperty("user_name")
    @JsonAlias("login")
    private String user_name;
    @JsonProperty("display_name")
    @JsonAlias("name")
    private String display_name;
    @JsonProperty("avatar")
    @JsonAlias("avatar_url")
    private String avatar;
    @JsonProperty("geo_location")
    @JsonAlias("location")
    private String geo_location;
    @JsonProperty(value = "email")
    private String email;
    @JsonProperty("url")
    @JsonAlias("html_url")
    private String url;
    @JsonProperty(value = "created_at")
    private LocalDateTime created_at;
}




