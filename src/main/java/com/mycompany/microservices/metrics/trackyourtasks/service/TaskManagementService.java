package com.mycompany.microservices.metrics.trackyourtasks.service;

import com.mycompany.microservices.metrics.trackyourtasks.model.InputTask;
import com.mycompany.microservices.metrics.trackyourtasks.model.OutputTask;
import com.mycompany.microservices.metrics.trackyourtasks.model.Task;
import com.mycompany.microservices.metrics.trackyourtasks.repos.TaskOperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fabrizio on 04/06/17.
 */
@Service
public class TaskManagementService implements ITaskManagementService {

    @Autowired
    public TaskOperationsRepository taskRepository;

    @Override
    public InputTask addTask(InputTask task) {
        if (task == null || task.getTaskId()==null)
            throw new IllegalArgumentException("Task is null");
        Task task1 = taskRepository.save(task);
        if (task1!=null) {
            return task;
        }
        return null;
    }

    @Override
    public OutputTask getTasksById(String id)
    {
        if (id == null)
            throw new IllegalArgumentException("Task Id is null");

        return taskRepository.findById(id);
    }

}
