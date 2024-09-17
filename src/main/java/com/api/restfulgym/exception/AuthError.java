package com.api.restfulgym.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties({"stackTrace", "cause", "suppressed", "localizedMessage", "status"})
public class AuthError extends RuntimeException {
    private final int status;
    private final String message;
    private final Date timestamp;

    public AuthError(int status, String message) {
        super(message);
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
