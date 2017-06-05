package com.mycompany.microservices.metrics.trackyourtasks.service;

import com.mycompany.microservices.metrics.trackyourtasks.model.InputTask;
import com.mycompany.microservices.metrics.trackyourtasks.model.OutputTask;

/**
 * Created by fabrizio on 04/06/17.
 */
public interface ITaskManagementService {
    InputTask addTask(InputTask store);
    OutputTask getTasksById(String id);
 }
