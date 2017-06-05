package com.mycompany.microservices.metrics.trackyourtasks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * One of arguments or input object is not valid. Response Code. 400
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidArgumentException extends IllegalArgumentException {
    public InvalidArgumentException() {
        super();
    }
    public InvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
    public InvalidArgumentException(String message) {
        super(message);
    }
    public InvalidArgumentException(Throwable cause) {
        super(cause);
    }
}
