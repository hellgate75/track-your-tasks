package com.mycompany.microservices.metrics.trackyourtasks.exceptions;

import com.mycompany.microservices.metrics.trackyourtasks.events.EventLogger;
import com.mycompany.microservices.metrics.trackyourtasks.model.RESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * REST Exception Handler, @RestControllerAdvice. Here we handle exceptions and log events.
 * @see RESTException
 * @see InternalException
 * @see TaskNotFoundException
 * @see InvalidArgumentException
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */
@RestControllerAdvice
public class ResponseExceptionHandler {

    @Autowired
    public EventLogger logger;

    /**
     * Handle Internal Generic Exception, related to unknown or unexpected application errors
     * @param ex Internal Generic Exception
     * @return RESTException Generic Internal Exception model
     */
    @ExceptionHandler(InternalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RESTException handleInternalException(final InternalException ex) {
        logger.logException(ex);
        return new RESTException(HttpStatus.INTERNAL_SERVER_ERROR, InternalException.class.getName(), ex.getMessage());
    }

    /**
     * Handle Task Not Found Exception, related to web query with empty responses
     * @param ex Task Not Found Exception
     * @return RESTException Generic Internal Exception model
     */
    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public RESTException handleTaskNotFoundException(final TaskNotFoundException ex) {
        logger.logException(ex);
        return new RESTException(HttpStatus.NOT_FOUND, TaskNotFoundException.class.getName(), ex.getMessage());
    }

    /**
     * Handle Invalid Argument Exception, related to web query with wrong or unexpected arguments
     * @param ex Invalid Argument Exception
     * @return RESTException Generic Internal Exception model
     */
    @ExceptionHandler(InvalidArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RESTException handleInvalidArgumentException(final InvalidArgumentException ex) {
        logger.logException(ex);
        return new RESTException(HttpStatus.BAD_REQUEST, IllegalArgumentException.class.getName(), ex.getMessage());
    }
}
