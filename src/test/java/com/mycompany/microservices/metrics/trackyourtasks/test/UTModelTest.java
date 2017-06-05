package com.mycompany.microservices.metrics.trackyourtasks.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.microservices.metrics.trackyourtasks.exceptions.TaskNotFoundException;
import com.mycompany.microservices.metrics.trackyourtasks.model.InputTask;
import com.mycompany.microservices.metrics.trackyourtasks.model.OutputTask;
import com.mycompany.microservices.metrics.trackyourtasks.model.RESTException;
import com.mycompany.microservices.metrics.trackyourtasks.model.Task;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Model Unit Tests
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */
public class UTModelTest implements UnitTest {

    @Test
    public void testInputTaskModelObject() throws IOException {
        String modelSampleJson = "{\"taskId\": \"mytaskid123\", \"duration\": 532}";
        ObjectMapper objectMapper = new ObjectMapper();
        InputTask parsedTask = objectMapper.readValue(modelSampleJson, InputTask.class);
        InputTask expectedTask = new InputTask();
        expectedTask.setTaskId("mytaskid123");
        expectedTask.setDuration(532);
        assertEquals("Expected right task Id", expectedTask.getTaskId(), parsedTask.getTaskId());
        assertEquals("Expected right duration", expectedTask.getDuration(), parsedTask.getDuration());
        assertEquals("Expected no changes in InputTask class parsing", expectedTask, parsedTask);
    }
    @Test
    public void testOutputTaskModelObject() throws IOException {
        String expectedJson = "{\"taskId\":\"mytaskid123\",\"average\":999.0}";
        OutputTask outputTask = new OutputTask();
        outputTask.setTaskId("mytaskid123");
        outputTask.setAverage(999);
        ObjectMapper objectMapper = new ObjectMapper();
        String outputJson = objectMapper.writeValueAsString(outputTask);
        assertEquals("Expected right task Id", "mytaskid123", outputTask.getTaskId()) ;
        assertEquals("Expected right average", 999d, outputTask.getAverage(), 0);
        assertEquals("Expected no changes in OutputTask json string", expectedJson, outputJson);
    }
    @Test
    public void testTaskModelObject() throws IOException {
        String taskId = "mytaskid123";
        long duration = 532;
        Task myTask = new Task(taskId, duration);
        assertEquals("Expected right task Id", myTask.getTaskId(), taskId) ;
        assertEquals("Expected right average", myTask.getDuration(), duration, 0);
    }
    @Test
    public void testTESTExceptionModelObject() throws IOException {
        String expectedJson = "{\"status\":\"NOT_FOUND\",\"name\":\"com.mycompany.microservices.metrics.trackyourtasks.exceptions.TaskNotFoundException\",\"message\":\"Task not found.\"}";
        RESTException outputException = new RESTException(HttpStatus.NOT_FOUND, TaskNotFoundException.class.getName(), "Task not found.");
        ObjectMapper objectMapper = new ObjectMapper();
        String outputJson = objectMapper.writeValueAsString(outputException);
        assertEquals("Expected right http status", HttpStatus.NOT_FOUND, outputException.getStatus()) ;
        assertEquals("Expected right exception class name", TaskNotFoundException.class.getName(), outputException.getName() ) ;
        assertEquals("Expected right exception class meessage", "Task not found.", outputException.getMessage() ) ;
        assertEquals("Expected no changes in RESTException json string", expectedJson, outputJson);
    }

}
