package com.mycompany.microservices.metrics.trackyourtasks.test;

import com.mycompany.microservices.metrics.trackyourtasks.events.Event;
import com.mycompany.microservices.metrics.trackyourtasks.events.EventLogger;
import com.mycompany.microservices.metrics.trackyourtasks.exceptions.InternalException;
import com.mycompany.microservices.metrics.trackyourtasks.exceptions.InvalidArgumentException;
import com.mycompany.microservices.metrics.trackyourtasks.exceptions.ResponseExceptionHandler;
import com.mycompany.microservices.metrics.trackyourtasks.exceptions.TaskNotFoundException;
import com.mycompany.microservices.metrics.trackyourtasks.service.TaskManagementService;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.IObjectFactory;
import org.testng.annotations.ObjectFactory;

/**
 * Event Management Elements Unit Tests.
 * At the moment it's missing any operative layer in logging test cases, due to missing specification for that layer.
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */
public class UTEventTest {

    @Mock
    public EventLogger eventLogger;

    private ResponseExceptionHandler handler;

    private final TaskNotFoundException taskNotFoundException = new TaskNotFoundException("Task not found.");

    private final InvalidArgumentException invalidArgumentException = new InvalidArgumentException("Task not found.");

    private final InternalException internalException = new InternalException("Task not found.");

    private final Event testEvent = new Event(20, "Massage here...",1);

    @Before
    public void initPowerMock() {
        PowerMockito.mock(TaskManagementService.class);
        MockitoAnnotations.initMocks(this);
        handler = new ResponseExceptionHandler();
        handler.logger = eventLogger;
    }

    @ObjectFactory
    public IObjectFactory getObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @Test
    public void testCallOfLogExceptionInHandleTaskNotFoundException() {
        PowerMockito.doAnswer(new Answer<Object>() {
            /**
             * Exception must be passed correctly from the top layer of code
             * @param invocation the invocation on the mock.
             * @return the value to be returned
             * @throws Throwable the throwable to be thrown
             */
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                TaskNotFoundException exception = invocation.getArgumentAt(0, TaskNotFoundException.class);
                assertNotNull("Exception reference must be passed correctly", exception);
                assertEquals("Exception must have same code ...", taskNotFoundException.getCode(), exception.getCode());
                assertEquals("Exception must have same description ...", taskNotFoundException.getDescription(), exception.getDescription());
                assertEquals("Exception must be posted into log ...", taskNotFoundException, exception);
                return true;
            }
        }).when(eventLogger).logException(taskNotFoundException);
        handler.handleTaskNotFoundException(taskNotFoundException);
    }

    @Test
    public void testCallOfLogExceptionInHandleInvalidArgumentException() {
        PowerMockito.doAnswer(new Answer<Object>() {
            /**
             * Exception must be passed correctly from the top layer of code
             * @param invocation the invocation on the mock.
             * @return the value to be returned
             * @throws Throwable the throwable to be thrown
             */
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                InvalidArgumentException exception = invocation.getArgumentAt(0, InvalidArgumentException.class);
                assertNotNull("Exception reference must be passed correctly", exception);
                assertEquals("Exception must have same code ...", invalidArgumentException.getCode(), exception.getCode());
                assertEquals("Exception must have same description ...", invalidArgumentException.getDescription(), exception.getDescription());
                assertEquals("Exception must be posted into log ...", invalidArgumentException, exception);
                return true;
            }
        }).when(eventLogger).logException(invalidArgumentException);
        handler.handleInvalidArgumentException(invalidArgumentException);
    }

    @Test
    public void testCallOfLogExceptionInHandleInternalException() {
        PowerMockito.doAnswer(new Answer<Object>() {
            /**
             * Exception must be passed correctly from the top layer of code
             * @param invocation the invocation on the mock.
             * @return the value to be returned
             * @throws Throwable the throwable to be thrown
             */
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                InternalException exception = invocation.getArgumentAt(0, InternalException.class);
                assertNotNull("Exception reference must be passed correctly", exception);
                assertEquals("Exception must have same code ...", internalException.getCode(), exception.getCode());
                assertEquals("Exception must have same description ...", internalException.getDescription(), exception.getDescription());
                assertEquals("Exception must be posted into log ...", internalException, exception);
                return true;
            }
        }).when(eventLogger).logException(internalException);
        handler.handleInternalException(internalException);
    }

    @Test
    public void testCallOfLogEvent() {
        PowerMockito.doAnswer(new Answer<Object>() {
            /**
             * @param invocation the invocation on the mock.
             * @return the value to be returned
             * @throws Throwable the throwable to be thrown
             */
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Event event = invocation.getArgumentAt(0, Event.class);
                assertNotNull("Event reference must be passed correctly", event);
                assertEquals("Event must have same code ...", testEvent.getCode(), event.getCode());
                assertEquals("Event must have same message ...", testEvent.getMessage(), event.getMessage());
                assertEquals("Event must have same category ...", testEvent.getCategory(), event.getCategory());
                assertEquals("Event must be posted into log ...", testEvent, event);
                return true;
            }
        }).when(eventLogger).logEvent(testEvent);
        eventLogger.logEvent(testEvent);
    }


}
