package com.mycompany.microservices.metrics.trackyourtasks.model;

import javax.persistence.*;

/**
 * Task Model
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */
@Entity
@Table(schema = "MS_TASKS", name = "TASKS")
public class Task {

    /**
     * Row Id
     */
    @Id
    @Column(name = "ID", insertable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Task Id
     */
    @Column(name = "TASKID", nullable = false)
    private String taskId;

    /**
     * Task Duration in milliseconds
     */
    @Column(name = "DURATION", nullable = false)
    private long duration;

    /**
     * Default Constructor for JPA
     */
    public Task() {
        super();
    }

    /**
     * Custom Constructor accepting fields
     * @param taskId - Task Id
     * @param duration - Task Duration in milliseconds
     */
    public Task(String taskId, long duration) {
        super();
        this.taskId = taskId;
        this.duration = duration;
    }

    /**
     * Getter for Task Id
     * @return String Task Id
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * Getter for Task Duration in milliseconds
     * @return long - Task Duration
     */
    public long getDuration() {
        return duration;
    }

    /**
     * Setter for Task Id
     * @param taskId - Task Id (String)
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * Getter for Task Duration in milliseconds
     * @param  duration - Task Duration (long)
     */
    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;
        if (id==null)
            return task.id==null;
        return id.equals(task.id);
    }

    @Override
    public int hashCode() {
        if (id==null)
            return super.hashCode();
        return id.hashCode();
    }
}