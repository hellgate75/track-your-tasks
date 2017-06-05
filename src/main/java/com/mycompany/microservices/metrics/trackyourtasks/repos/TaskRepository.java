package com.mycompany.microservices.metrics.trackyourtasks.repos;

import com.mycompany.microservices.metrics.trackyourtasks.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Database JPA Repository for Task Database Model Operations
 * @see Task
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

    /**
     * Find Task objects, in the Tasks table, by taskId
     * @param taskId - Task Id I am looking for task list
     * @return List - List of matching Tasks in database
     */
    List<Task> findByTaskId(String taskId);
}