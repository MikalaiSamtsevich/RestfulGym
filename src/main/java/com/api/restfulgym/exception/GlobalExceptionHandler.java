package com.api.restfulgym.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    private ResponseEntity<AuthError> badCredExceptionHandler(AuthError e) {
        return new ResponseEntity<>(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthError.class)
    private ResponseEntity<AuthError> authError(AuthError e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
