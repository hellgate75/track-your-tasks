package com.mycompany.microservices.metrics.trackyourtasks.controller;

import com.mycompany.microservices.metrics.trackyourtasks.exceptions.InternalException;
import com.mycompany.microservices.metrics.trackyourtasks.exceptions.InvalidArgumentException;
import com.mycompany.microservices.metrics.trackyourtasks.exceptions.TaskNotFoundException;
import com.mycompany.microservices.metrics.trackyourtasks.model.InputTask;
import com.mycompany.microservices.metrics.trackyourtasks.model.OutputTask;
import com.mycompany.microservices.metrics.trackyourtasks.service.TaskManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller exposing service endpoints
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */
@RestController
@RequestMapping("/tasks")
public class TaskManagementController {
    /**
     * Task Operations Service
     */
    @Autowired
    public TaskManagementService taskService;

    /**
     * Task Duration Average Statistic Endpoint
     * @param taskId - Required task id string (Path Variable)
     * @return OutputTask - Task Duration Average Model Object
     * @throws TaskNotFoundException Thrown when the task Id has no internal match
     */
    @RequestMapping(value = "/stats/{taskId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    OutputTask getTasks(@PathVariable String taskId) throws TaskNotFoundException {
        try{
            OutputTask task = taskService.getTasksById(taskId);
            if (task == null) {
                throw new TaskNotFoundException("Task id " + taskId + " not found!!");
            }
            return task;
        } catch (IllegalArgumentException exception) {
            throw new InvalidArgumentException(exception.getMessage());
        } catch (TaskNotFoundException exc) {
            throw exc;
        } catch (Exception ex) {
            throw new InternalException(ex);
        }
    }

    /**
     * Task Insert Endpoint
     * @param task - Required Task Input Model (Request Body)
     * @return String - Plain text response with following values : "ok" and "error"
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String taskAdd(@RequestBody InputTask task) {
        try{
            InputTask addedTask = taskService.addTask(task);
            return addedTask != null ? "ok" : "error";
        } catch (Throwable ex) {
            return "error";
        }
    }

}