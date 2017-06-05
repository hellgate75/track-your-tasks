package com.mycompany.microservices.metrics.trackyourtasks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Internal Exception used for Unknown Internal Failures. Response Code. 500
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalException extends RuntimeException {
    public InternalException() {
        super();
    }
    public InternalException(String message, Throwable cause) {
        super(message, cause);
    }
    public InternalException(String message) {
        super(message);
    }
    public InternalException(Throwable cause) {
        super(cause);
    }
}
