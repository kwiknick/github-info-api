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
    private ErrorCode errorCode;
    private boolean recoverable;

    GithubInfoApiErrorResponse(String description) {
        this.source = APPLICATION_NAME;
        this.description = description;
        this.errorCode = ErrorCode.UNSPECIFIED;
        this.recoverable = false;
    }

    GithubInfoApiErrorResponse(String description, boolean recoverable) {
        this.source = APPLICATION_NAME;
        this.description = description;
        this.errorCode = ErrorCode.UNSPECIFIED;
        this.recoverable = recoverable;
    }

    GithubInfoApiErrorResponse(String description, ErrorCode errorCode) {
        this.source = APPLICATION_NAME;
        this.description = description + " ErrorCode Message: " + errorCode.getMessage();
        this.errorCode = errorCode;
        this.recoverable = false;
    }

    GithubInfoApiErrorResponse(String description, ErrorCode errorCode, boolean recoverable) {
        this.source = APPLICATION_NAME;
        this.description = description + " ErrorCode Message: " + errorCode.getMessage();
        this.errorCode = errorCode;
        this.recoverable = recoverable;
    }

    GithubInfoApiErrorResponse(ErrorCode errorCode) {
        this.source = APPLICATION_NAME;
        this.description = errorCode.getMessage();
        this.errorCode = errorCode;
        this.recoverable = false;
    }

    GithubInfoApiErrorResponse(ErrorCode errorCode, boolean recoverable) {
        this.source = APPLICATION_NAME;
        this.description = errorCode.getMessage();
        this.errorCode = errorCode;
        this.recoverable = recoverable;
    }
}
