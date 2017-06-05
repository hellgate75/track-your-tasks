package com.mycompany.microservices.metrics.trackyourtasks.repos;

import com.mycompany.microservices.metrics.trackyourtasks.model.OutputTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Database JPA Repository for Task Duration Average Database Model Operations
 * @see OutputTask
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */
@Repository
public interface TaskOutputRepository extends JpaRepository<OutputTask, String> {
    /**
     * Find Task Average Output object, in the AVERAGE table, by Task ID
     * @param taskId - Task Id I am looking for average duration
     * @return Optional - Future result for lookup
     */
    Optional<OutputTask> findByTaskId(String taskId);
}