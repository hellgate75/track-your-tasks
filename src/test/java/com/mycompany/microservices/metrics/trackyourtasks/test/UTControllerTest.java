package com.mycompany.microservices.metrics.trackyourtasks.test;

import com.mycompany.microservices.metrics.trackyourtasks.controller.TaskManagementController;
import com.mycompany.microservices.metrics.trackyourtasks.exceptions.InternalException;
import com.mycompany.microservices.metrics.trackyourtasks.exceptions.InvalidArgumentException;
import com.mycompany.microservices.metrics.trackyourtasks.exceptions.TaskNotFoundException;
import com.mycompany.microservices.metrics.trackyourtasks.model.InputTask;
import com.mycompany.microservices.metrics.trackyourtasks.model.OutputTask;
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
public class UTControllerTest implements UnitTest {


    @Mock
    public TaskManagementService service;

    public TaskManagementController taskManagementController;

    @Before
    public void initPowerMock() {
        testInputTask = new InputTask();
        testInputTask.setTaskId("dasdasd");
        testInputTask.setDuration(200);
        testErrorInputTask = new InputTask();
        testOutputTask = new OutputTask();
        testOutputTask.setTaskId("dasdasd");
        testOutputTask.setAverage(200d);
        testWrongInputTask = new InputTask();
        testErrorInputTask.setTaskId("sdfsdfs");
        PowerMockito.mock(TaskManagementService.class);
        MockitoAnnotations.initMocks(this);
        taskManagementController = new TaskManagementController();
        taskManagementController.taskService = service;
        PowerMockito.when(service.addTask(testInputTask)).thenReturn(testInputTask);
        PowerMockito.when(service.addTask(testErrorInputTask)).thenThrow(NullPointerException.class);
        PowerMockito.when(service.getTasksById(testTaskId)).thenReturn(testOutputTask);
        PowerMockito.when(service.getTasksById(testWrongTaskId)).thenThrow(IllegalArgumentException.class);
        PowerMockito.when(service.getTasksById(testNotPresentTaskId)).thenReturn(null);
        PowerMockito.when(service.getTasksById(testInternalErrorTaskId)).thenThrow(NullPointerException.class);
    }

    private InputTask testInputTask;
    private InputTask testErrorInputTask;
    private InputTask testWrongInputTask;
    private OutputTask testOutputTask;

    public String testTaskId = "dsfsfasdfasf";

    public String testNotPresentTaskId = "notPresetTaskId";

    public String testWrongTaskId = null;

    public String testInternalErrorTaskId = "errorCausesInternalError";

    @ObjectFactory
    public IObjectFactory getObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @Test
    public void testCorrectInputObjectAddRest() {
        String output = taskManagementController.taskAdd(testInputTask);
        String expected = "ok";
        assertEquals("Rest call must be evaluated correctly", expected, output);
    }

    @Test
    public void testSimulateIncorrectInputObjectAddRest() {
        String output = taskManagementController.taskAdd(testWrongInputTask);
        String expected = "error";
        assertEquals("Rest call must be evaluated correctly", expected, output);
    }

    @Test
    public void testSimulateIllegalArgumentErrorWhenAddingTaskInRest() {
        testWrongInputTask.setTaskId(null);
        String output = taskManagementController.taskAdd(testWrongInputTask);
        String expected = "error";
        assertEquals("Rest call must be evaluated correctly", expected, output);
    }

    @Test(expected = InternalException.class)
    public void testSimulateInternalErrorWhenAddingTaskInRest() {
        String output = taskManagementController.taskAdd(testErrorInputTask);
        String expected = "error";
        assertEquals("Rest call must be evaluated correctly", expected, output);
    }

    @Test
    public void testCorrectQueryForTaskStats() throws TaskNotFoundException {
        OutputTask outputTask = taskManagementController.getTasks(testTaskId);
        assertEquals("Rest call must be evaluated correctly", testOutputTask, outputTask);
    }

    @Test(expected = InvalidArgumentException.class)
    public void tesSimulateInCorrectQueryForTaskStats() throws TaskNotFoundException {
        OutputTask outputTask = taskManagementController.getTasks(testWrongTaskId);
        assertEquals("Rest call must be evaluated correctly", null, outputTask);
    }

    @Test(expected = TaskNotFoundException.class)
    public void tesSimulateEmptyResponseQueryForTaskStats() throws TaskNotFoundException {
        OutputTask outputTask = taskManagementController.getTasks(testNotPresentTaskId);
        assertEquals("Rest call must be evaluated correctly", null, outputTask);
    }

    @Test(expected = InternalException.class)
    public void tesSimulateInternalErrorOnQueryForTaskStats() throws TaskNotFoundException {
        OutputTask outputTask = taskManagementController.getTasks(testInternalErrorTaskId);
        assertEquals("Rest call must be evaluated correctly", null, outputTask);
    }

}
