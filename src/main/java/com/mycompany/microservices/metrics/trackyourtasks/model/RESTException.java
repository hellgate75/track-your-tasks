package com.mycompany.microservices.metrics.trackyourtasks.model;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.http.HttpStatus;

/**
 * REST Exception Model object for the Rest Controller Advice
 * In a real world for any exception should be associated to specific code, at it should be classified
 * in order to specific weight, area, UI handling, counteractions or simply for monitoring.
 * Not architecture design, no MVP rules, no Assumption map = uncertain coding requirements
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */
@JsonComponent
public class RESTException {
    private HttpStatus status;
    private String name;
    private String message;

    /**
     * Default Generic Constructor
     */
    public RESTException() {
        super();
    }

    /**
     * Custom Constructor, referring to class fields
     * @param status HTTP Error Status
     * @param name Exception class name
     * @param message Exception message
     */

    public RESTException(HttpStatus status, String name, String message) {
        super();
        this.status = status;
        this.name = name;
        this.message = message;
    }

    /**
     * Returns the HTTP Status for the exception
     * @return HttpStatus - Http Status Error
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Returns the thrown exception class name
     * @return String - clause exception class name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the thrown exception message
     * @return String - clause exception class message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the HTTP Status for the exception
     * @param status  - Http Status Error (HttpStatus)
     */
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    /**
     * Sets the thrown exception class name
     * @param name  - clause exception class name (String)
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the thrown exception class message
     * @param message  - clause exception class name (String)
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Default print behaviour
     * @return String Print representation for the model
     */
    @Override
    public java.lang.String toString() {
        return getClass().getName()+" : " + name + "[status : " + status.value() + "] = " + message;
    }
}
