package com.mycompany.microservices.metrics.trackyourtasks.test;

import com.mycompany.microservices.metrics.trackyourtasks.model.InputTask;
import com.mycompany.microservices.metrics.trackyourtasks.model.OutputTask;
import com.mycompany.microservices.metrics.trackyourtasks.model.Task;
import com.mycompany.microservices.metrics.trackyourtasks.repos.TaskOperationsRepository;
import com.mycompany.microservices.metrics.trackyourtasks.repos.TaskOutputRepository;
import com.mycompany.microservices.metrics.trackyourtasks.repos.TaskRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * JPA Repository Unit Tests
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */
@RunWith(PowerMockRunner.class)
public class UTRepositoryTest implements UnitTest {


    @Mock
    public TaskRepository repository;

    @Mock
    public TaskOutputRepository outputRepository;

    public TaskOperationsRepository operationsRepository;

    public String testTaskId = "dsfsfasdfasf";

    public List<Task> tasks = new ArrayList<>(0);

    private InputTask testInputTask;
    private OutputTask testOutputTask;

    private Task testTask;

    private Optional<OutputTask> answer;

    @Before
    public void initPowerMock() {
        testInputTask = new InputTask();
        testInputTask.setTaskId("dasdasd");
        testInputTask.setDuration(200);
        testOutputTask = new OutputTask();
        testOutputTask.setTaskId("dasdasd");
        testOutputTask.setAverage(200d);
        testTask = new Task("dasdasd", 200);
        PowerMockito.mock(TaskRepository.class);
        PowerMockito.mock(TaskOutputRepository.class);
        MockitoAnnotations.initMocks(this);
        operationsRepository = new TaskOperationsRepository();
        operationsRepository.repository = repository;
        operationsRepository.outputRepository = outputRepository;
        tasks.add(testTask);
        PowerMockito.when(repository.findByTaskId(testTaskId)).thenReturn(tasks);
        answer = Optional.ofNullable(testOutputTask);
        PowerMockito.when(outputRepository.findByTaskId(testTaskId)).thenReturn(answer);
    }

    @ObjectFactory
    public IObjectFactory getObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @Test
    public void testCorrectInputObjectAddInDatabase() {
        Task task = operationsRepository.save(testInputTask);
        assertEquals("task Database save must be evaluated correctly", testTask, task);
    }

    @Test
    public void testSimulateIncorrectInputObjectAddInDatabase() {
        PowerMockito.when(repository.findByTaskId(testInputTask.getTaskId())).thenReturn(null);
        Task task = operationsRepository.save(testInputTask);
        assertEquals("task Database save must be evaluated correctly", null, task);
    }

    @Test
    public void testCorrectStatsFromDatabase() {
        OutputTask task = operationsRepository.findById(testTaskId);
        assertEquals("output task Database stats object must have right task Id", testOutputTask.getTaskId(), task.getTaskId());
        assertEquals("output task Database stats object must have right duration Average", testOutputTask.getAverage(), task.getAverage(), 0);
        assertEquals("output task Database stats object must be evaluated correctly", testOutputTask, task);
    }


    @Test
    public void testSimulateCorrectStatsFromDatabase() {
        OutputTask nullTask=null;
        answer = Optional.ofNullable(nullTask);
        PowerMockito.when(outputRepository.findByTaskId(testTaskId)).thenReturn(answer);
        OutputTask task = operationsRepository.findById(testTaskId);
        assertEquals("output task Database stats object must be evaluated correctly", null, task);
    }


}
