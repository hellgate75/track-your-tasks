package com.mycompany.microservices.metrics.trackyourtasks.test;

import com.mycompany.microservices.metrics.trackyourtasks.model.InputTask;
import com.mycompany.microservices.metrics.trackyourtasks.model.OutputTask;
import com.mycompany.microservices.metrics.trackyourtasks.model.Task;
import com.mycompany.microservices.metrics.trackyourtasks.repos.TaskOperationsRepository;
import com.mycompany.microservices.metrics.trackyourtasks.service.TaskManagementService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.IObjectFactory;
import org.testng.annotations.ObjectFactory;

import static org.junit.Assert.assertEquals;

/**
 * REST Application Service Unit Tests
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */
@RunWith(PowerMockRunner.class)
public class UTServiceTest implements UnitTest {


    @Mock
    public TaskOperationsRepository repository;

    public TaskManagementService service;

    @Before
    public void initPowerMock() {
        testInputTask = new InputTask();
        testInputTask.setTaskId("dasdasd");
        testInputTask.setDuration(200);
        PowerMockito.mock(TaskOperationsRepository.class);
        MockitoAnnotations.initMocks(this);
        service = new TaskManagementService();
        service.taskRepository = repository;
    }

    private InputTask testInputTask;

    @ObjectFactory
    public IObjectFactory getObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @Test
    public void testCorrectInputObjectAddInDatabase() {
        PowerMockito.when(repository.save(testInputTask)).thenReturn(new Task(testInputTask.getTaskId(), testInputTask.getDuration()));
        InputTask task = service.addTask(testInputTask);
        assertEquals("task Database call must be evaluated correctly", testInputTask, task);
    }

    @Test
    public void testSimulateIncorrectInputObjectAddInDatabase() {
        PowerMockito.when(repository.save(testInputTask)).thenReturn(null);
        InputTask task = service.addTask(testInputTask);
        assertEquals("task Database call must be evaluated correctly", null, task);
    }

    @Test
    public void testCorrectQueryForStatistics() {
        String testTaskId = "dsfsfasdfasf";
        OutputTask outputTask = new OutputTask();
        outputTask.setTaskId(testTaskId);
        outputTask.setAverage(240.0);
        PowerMockito.when(repository.findById(testTaskId)).thenReturn(outputTask);
        OutputTask task = service.getTasksById(testTaskId);
        assertEquals("output task Database query object  must have right task Id", outputTask.getTaskId(), task.getTaskId());
        assertEquals("output task Database query object  must have right duration Average", outputTask.getAverage(), task.getAverage(), 0);
        assertEquals("output task database query object must be correct", outputTask, task);
    }

    @Test
    public void testSimulateIncorrectQueryForStatistics() {
        String testTaskId = "dsfsfasdfasf";
        PowerMockito.when(repository.findById(testTaskId)).thenReturn(null);
        OutputTask task = service.getTasksById(testTaskId);
        assertEquals("output task database query object must be null", null, task);
    }

}
