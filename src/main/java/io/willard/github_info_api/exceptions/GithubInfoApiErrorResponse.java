package io.willard.github_info_api.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GithubInfoApiErrorResponse {
    private static final long serialVersionUID = 1L;
    public static final String APPLICATION_NAME = "Github Info API";

    private String source;
    private String description;
    private boolean recoverable;

    GithubInfoApiErrorResponse(String description, boolean recoverable) {
        this.source = APPLICATION_NAME;
        this.description = description;
        this.recoverable = recoverable;
    }
}
