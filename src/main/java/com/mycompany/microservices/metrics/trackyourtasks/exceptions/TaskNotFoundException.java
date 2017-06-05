package com.mycompany.microservices.metrics.trackyourtasks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Task not found after database lookup. Response Code. 404
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TaskNotFoundException  extends NoSuchFieldException implements IRestExeption {
    public TaskNotFoundException() {
        super();
    }
    public TaskNotFoundException(String message) {
        super(message);
    }

    @Override
    public int getCode() {
        //Fake code
        return HttpStatus.NOT_FOUND.value();
    }

    @Override
    public String getDescription() {
        return this.getMessage();
    }

}
