package io.willard.github_info_api.exceptions;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_REQUEST(400, "Invalid request parameters"),
    UNAUTHORIZED(401, "Unauthorized access"),
    FORBIDDEN(403, "Access is forbidden"),
    NOT_FOUND(404, "Resource not found"),
    INTERNAL_SERVER_ERROR(500, "An unexpected error occurred"),
    SERVICE_UNAVAILABLE(503, "Service is temporarily unavailable"),
    UNSPECIFIED(9000, "An Unspecified Error has Occurred");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
