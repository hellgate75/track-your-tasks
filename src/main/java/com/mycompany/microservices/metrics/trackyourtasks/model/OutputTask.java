package com.mycompany.microservices.metrics.trackyourtasks.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Task REST Output Model
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */
@Entity
@Table(schema = "MS_TASKS", name = "AVERAGE")
public class OutputTask {

    /**
     * Task Id
     */
    @Id
    @Column(name = "TASKID", nullable = false)
    private String taskId;

    /**
     * Task Duration Average in milliseconds
     */
    @Column(name = "AVERAGE", nullable = false)
    private double average;

    /**
     * Default Constructor for JPA
     */
    public OutputTask() {

    }

    /**
     * Getter for Task Id
     * @return Task Id (String)
     */
    public String getTaskId() {
        return this.taskId;
    }

    /**
     * Getter for Task Duration
     * @return double - Task Duration Average in milliseconds
     */
    public double getAverage() {
        return this.average;
    }

    /**
     * Setter for Task Id
     * @param taskId - Task Id (String)
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * Getter for Task Duration
     * @param average - Task Duration Average in milliseconds (double)
     */
    public void setAverage(double average) {
        this.average = average;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutputTask)) return false;

        OutputTask output = (OutputTask) o;
        if (taskId==null)
            return output.taskId==null;

        return taskId.equals(output.taskId);
    }

    @Override
    public int hashCode() {
        if (taskId==null)
            return super.hashCode();
        return taskId.hashCode();
    }
}