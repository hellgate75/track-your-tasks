package com.mycompany.microservices.metrics.trackyourtasks.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Task REST Input Model
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */
@Entity
public class InputTask {

    /**
    * Task Id
    */
    @Id
    private String taskId;

    /**
     * Task Duration
     */
    private long duration;

    /**
     * Default Constructor for JSON Parser
     */
    public InputTask() {

    }

    /**
     * Getter for Task Id
     * @return Task Id (String)
     */
    public String getTaskId() {
        return this.taskId;
    }

    /**
     * Getter for Task Duration in milliseconds
     * @return Duration (long)
     */
    public long getDuration() {
        return this.duration;
    }

    /**
     * Setter for Task Id
     * @param taskId - Task Id (String)
     */
    @JsonProperty(value = "taskId")
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * Getter for Task Duration in milliseconds
     * @param duration - Task Duration in milliseconds (long)
     */
    @JsonProperty(value = "duration")
    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InputTask)) return false;

        InputTask taskInput = (InputTask) o;

        if (taskId==null)
            return taskInput.taskId==null;
        if (duration != taskInput.duration) return false;
        return taskId.equals(taskInput.taskId);
    }

    @Override
    public int hashCode() {
        int result = taskId!=null ? taskId.hashCode() : super.hashCode();
        result = 31 * result + (int) (duration ^ (duration >>> 32));
        return result;
    }
}