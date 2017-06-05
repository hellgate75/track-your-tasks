package com.mycompany.microservices.metrics.trackyourtasks.repos;

import com.mycompany.microservices.metrics.trackyourtasks.model.InputTask;
import com.mycompany.microservices.metrics.trackyourtasks.model.OutputTask;
import com.mycompany.microservices.metrics.trackyourtasks.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

/**
 * Compute Component based on Task Input and Task Duration Average Output Model
 * @see TaskRepository
 * @see TaskOutputRepository
 * @see Task
 * @see OutputTask
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */
@Component
public class TaskOperationsRepository {

    /**
     * Task Repository for task records recovery
     */
    @Autowired
    public TaskRepository repository;

    /**
     * Task Output Repository for output average task records recovery
     */
    @Autowired
    public TaskOutputRepository outputRepository;

    /**
     * Find Task Average Duration from own TaskId
     * @param taskId - Task ID we are looking for
     * @return OutputTask - Task Average Duration Output
     */
    public OutputTask findById(String taskId) {
        Optional<OutputTask> taskOptional = outputRepository.findByTaskId(taskId);
        if (taskOptional.isPresent()) {
            return taskOptional.get();
        }
        return null;
    }

    /**
     * Save Input Task information and calculate/update average
     * @param taskInput - Input Task element, from REST service
     * @return Task - Just inserted Task element
     */
    public Task save(InputTask taskInput) {
        if (taskInput == null || taskInput.getTaskId() == null) {
            return null;
        }
        Task task = new Task(taskInput.getTaskId(), taskInput.getDuration());
        repository.saveAndFlush(task);
        List<Task>  tasks = repository.findByTaskId(task.getTaskId());
        if (tasks==null) {
            return null;
        }
        double totalDuration = tasks.parallelStream().flatMapToDouble(task1 -> DoubleStream.of(task1.getDuration()) ).sum();
        double average = totalDuration / ((double)tasks.size());
        OutputTask output = new OutputTask();
        output.setTaskId(task.getTaskId());
        output.setAverage(average);
        outputRepository.saveAndFlush(output);
        return task;
    }

}