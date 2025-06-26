package io.willard.github_info_api.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GithubInfoApiExceptionHandler {
    private static final Logger log = LogManager.getLogger();

    @ExceptionHandler(GithubInfoApiException.class)
    public ResponseEntity<GithubInfoApiErrorResponse> handleGithubInfoApiException(GithubInfoApiException ex) {
        log.error("GithubInfoApiException was caught", ex);
        GithubInfoApiErrorResponse response = new GithubInfoApiErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public final ResponseEntity<Object> handleException(HttpClientErrorException ex) {
        log.error("HttpClientErrorException was caught", ex);
        GithubInfoApiErrorResponse errorResponse = new GithubInfoApiErrorResponse(ex.getMessage(), false);
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleException(ConstraintViolationException ex) {
        log.error("A ConstraintViolationException was caught", ex);
        GithubInfoApiErrorResponse errorResponse = new GithubInfoApiErrorResponse(ex.getMessage(), false);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleException(Exception ex) {
        log.error("An Unhandled Exception was thrown", ex);
        GithubInfoApiErrorResponse errorResponse = new GithubInfoApiErrorResponse(ex.getMessage(), false);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
