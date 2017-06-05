package com.mycompany.microservices.metrics.trackyourtasks.events;

/**
 * Event Model used to define metrics to log over log services
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */

public class Event {

    private int code;

    private String message;

    private int category;

    public Event() {
        super();
    }

    public Event(int code, String message, int category) {
        super();
        this.code = code;
        this.message = message;
        this.category = category;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getCategory() {
        return category;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
