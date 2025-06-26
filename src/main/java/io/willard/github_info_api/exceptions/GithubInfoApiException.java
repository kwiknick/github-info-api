package io.willard.github_info_api.exceptions;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GithubInfoApiException extends RuntimeException {
    private final ErrorCode errorCode;

    public GithubInfoApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public GithubInfoApiException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }
}