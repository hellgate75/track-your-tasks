package com.mycompany.microservices.metrics.trackyourtasks.test;

import com.mycompany.microservices.metrics.trackyourtasks.Application;
import com.mycompany.microservices.metrics.trackyourtasks.exceptions.TaskNotFoundException;
import com.mycompany.microservices.metrics.trackyourtasks.model.InputTask;
import com.mycompany.microservices.metrics.trackyourtasks.model.OutputTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Integration Tests
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ITApplicationTest {
    private RestTemplate restTemplate = new RestTemplate();

    @Value("${test.server.port}")
    private String serverPort;

    @Test
    public void integrationTestAddAndStatsTasksTogether() {
        assertAddAndStatsWorksTogether("sdfsdfsdfadf", 5);
    }

    @Test(expected = HttpClientErrorException.class)
    public void integrationTestStatsNotExistingTaskId() throws TaskNotFoundException {
        //Exception : 404 NOT FOUND
        assertTaskIdNotExists("kjgkjbkjbkbk");
    }

    @Test(expected = HttpClientErrorException.class)
    public void integrationTestStatsVeryLongTaskId() throws TaskNotFoundException {
        //51 chars out of database id size with this implementation (max: 50)
        double average = addTaskRandom("fislkfsnasfislkfsnasfislkfsnasfislkfsnasfislkfsnasr", 500, 5);
        checkTaskStats("fislkfsnasfislkfsnasfislkfsnasfislkfsnasfislkfsnasr", average);
    }

    @Test(expected = HttpClientErrorException.class)
    public void integrationTestStatsVeryLongForceSearchByTaskId() throws TaskNotFoundException {
        double average = addTaskRandom("fislkfsnasfislkfsnasfislkfsnasfislkfsnasfislkfsnas", 500, 5);
        //51 chars out of database id size with this implementation (max: 50)
        //Exception : 404 NOT FOUND
        checkTaskStats("fislkfsnasfislkfsnasfislkfsnasfislkfsnasfislkfsnasr", 0d);
    }

    private void assertAddAndStatsWorksTogether(String taskId, int tests) {
        double average = addTaskRandom(taskId, 500, tests);
        checkTaskStats(taskId, average);
    }

    private void assertTaskIdNotExists(String taskId) {
        checkTaskStats(taskId, 200d);
    }

    private double addTaskRandom(String taskId, int round, int tests) {
        double durationSum = 0;
        for (int i=0; i<tests; i++) {
            String url = "http://127.0.0.1:"+serverPort+"/tasks/add";
            long duration = new Random().nextInt(round);
            durationSum += duration;
            InputTask myInput = new InputTask();
            myInput.setTaskId(taskId);
            myInput.setDuration(duration);
            ResponseEntity<String> answer = restTemplate.postForEntity(url, myInput, String.class);
            assertEquals("Answer must be positive after POST", "ok", answer.getBody());
        }
        return (durationSum/((double)tests));
    }
    private void checkTaskStats(String taskId, double average) {
        String url = String.format("http://127.0.0.1:"+serverPort+"/tasks/stats/%s", taskId);
        OutputTask outputObject = restTemplate.getForObject(url, OutputTask.class);
        assertNotNull("App Must return one element", outputObject);
        assertEquals("Task Id must match", taskId, outputObject.getTaskId());
        assertEquals(Math.ceil(average), Math.ceil(outputObject.getAverage()), 0);
    }
}

