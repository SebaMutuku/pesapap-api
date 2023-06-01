package com.pesapap.apiv1.api;

import com.pesapap.apiv1.dto.StudentPaymentResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestControllerExceptionAdviceTest {
    /**
     * Method under test: {@link RestControllerExceptionAdvice#handledException(Exception)}
     */
    @Test
    void testHandledException() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        RestControllerExceptionAdvice restControllerExceptionAdvice = new RestControllerExceptionAdvice();
        ResponseEntity<Object> actualHandledExceptionResult = restControllerExceptionAdvice
                .handledException(new Exception("foo"));
        assertTrue(actualHandledExceptionResult.hasBody());
        assertEquals(500, actualHandledExceptionResult.getStatusCodeValue());
        assertTrue(actualHandledExceptionResult.getHeaders().isEmpty());
        assertNull(((StudentPaymentResponse) actualHandledExceptionResult.getBody()).payload());
        assertEquals("foo", ((StudentPaymentResponse) actualHandledExceptionResult.getBody()).message());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                ((StudentPaymentResponse) actualHandledExceptionResult.getBody()).httpStatus());
    }

    /**
     * Method under test: {@link RestControllerExceptionAdvice#handledException(Exception)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandledException2() {

        (new RestControllerExceptionAdvice()).handledException((Exception) null);
    }


    @Test
    void testHandledException3() {

        RestControllerExceptionAdvice restControllerExceptionAdvice = new RestControllerExceptionAdvice();
        ResponseEntity<Object> actualHandledExceptionResult = restControllerExceptionAdvice
                .handledException(new RuntimeException("foo"));
        assertTrue(actualHandledExceptionResult.hasBody());
        assertEquals(500, actualHandledExceptionResult.getStatusCodeValue());
        assertTrue(actualHandledExceptionResult.getHeaders().isEmpty());
        assertNull(((StudentPaymentResponse) actualHandledExceptionResult.getBody()).payload());
        assertEquals("foo", ((StudentPaymentResponse) actualHandledExceptionResult.getBody()).message());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                ((StudentPaymentResponse) actualHandledExceptionResult.getBody()).httpStatus());
    }

    @Test
    void testHandledException4() {

        (new RestControllerExceptionAdvice()).handledException((RuntimeException) null);
    }

    /**
     * Method under test: {@link RestControllerExceptionAdvice#handledException(NoSuchElementException)}
     */
    @Test
    void testHandledException5() {

        RestControllerExceptionAdvice restControllerExceptionAdvice = new RestControllerExceptionAdvice();
        ResponseEntity<Object> actualHandledExceptionResult = restControllerExceptionAdvice
                .handledException(new NoSuchElementException("foo"));
        assertTrue(actualHandledExceptionResult.hasBody());
        assertEquals(400, actualHandledExceptionResult.getStatusCodeValue());
        assertTrue(actualHandledExceptionResult.getHeaders().isEmpty());
        assertNull(((StudentPaymentResponse) actualHandledExceptionResult.getBody()).payload());
        assertEquals("foo", ((StudentPaymentResponse) actualHandledExceptionResult.getBody()).message());
        assertEquals(HttpStatus.BAD_REQUEST,
                ((StudentPaymentResponse) actualHandledExceptionResult.getBody()).httpStatus());
    }

    /**
     * Method under test: {@link RestControllerExceptionAdvice#handledException(NoSuchElementException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandledException6() {

        (new RestControllerExceptionAdvice()).handledException((NoSuchElementException) null);
    }

    /**
     * Method under test: {@link RestControllerExceptionAdvice#handledException(AccessDeniedException)}
     */
    @Test
    void testHandledException7() {
        RestControllerExceptionAdvice restControllerExceptionAdvice = new RestControllerExceptionAdvice();
        ResponseEntity<Object> actualHandledExceptionResult = restControllerExceptionAdvice
                .handledException(new AccessDeniedException("Msg"));
        assertTrue(actualHandledExceptionResult.hasBody());
        assertEquals(401, actualHandledExceptionResult.getStatusCodeValue());
        assertTrue(actualHandledExceptionResult.getHeaders().isEmpty());
        assertNull(((StudentPaymentResponse) actualHandledExceptionResult.getBody()).payload());
        assertEquals("Msg", ((StudentPaymentResponse) actualHandledExceptionResult.getBody()).message());
        assertEquals(HttpStatus.UNAUTHORIZED,
                ((StudentPaymentResponse) actualHandledExceptionResult.getBody()).httpStatus());
    }

    /**
     * Method under test: {@link RestControllerExceptionAdvice#handledException(AccessDeniedException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandledException8() {

        (new RestControllerExceptionAdvice()).handledException((AccessDeniedException) null);
    }

    /**
     * Method under test: {@link RestControllerExceptionAdvice#handledException(NoHandlerFoundException)}
     */
    @Test
    void testHandledException9() {

        RestControllerExceptionAdvice restControllerExceptionAdvice = new RestControllerExceptionAdvice();
        ResponseEntity<Object> actualHandledExceptionResult = restControllerExceptionAdvice.handledException(
                new NoHandlerFoundException("https://example.org/example", "https://example.org/example", new HttpHeaders()));
        assertTrue(actualHandledExceptionResult.hasBody());
        assertEquals(404, actualHandledExceptionResult.getStatusCodeValue());
        assertTrue(actualHandledExceptionResult.getHeaders().isEmpty());
        assertNull(((StudentPaymentResponse) actualHandledExceptionResult.getBody()).payload());
        assertEquals("No endpoint https://example.org/example https://example.org/example.",
                ((StudentPaymentResponse) actualHandledExceptionResult.getBody()).message());
        assertEquals(HttpStatus.NOT_FOUND,
                ((StudentPaymentResponse) actualHandledExceptionResult.getBody()).httpStatus());
    }


    @Test
    void testHandledException10() {
        (new RestControllerExceptionAdvice()).handledException((NoHandlerFoundException) null);
    }

    /**
     * Method under test: {@link RestControllerExceptionAdvice#handledException(NoHandlerFoundException)}
     */
    @Test
    void testHandledException11() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        RestControllerExceptionAdvice restControllerExceptionAdvice = new RestControllerExceptionAdvice();
        NoHandlerFoundException exception = mock(NoHandlerFoundException.class);
        when(exception.getMessage()).thenReturn("Not all who wander are lost");
        ResponseEntity<Object> actualHandledExceptionResult = restControllerExceptionAdvice.handledException(exception);
        assertTrue(actualHandledExceptionResult.hasBody());
        assertEquals(404, actualHandledExceptionResult.getStatusCodeValue());
        assertTrue(actualHandledExceptionResult.getHeaders().isEmpty());
        assertNull(((StudentPaymentResponse) actualHandledExceptionResult.getBody()).payload());
        assertEquals("Not all who wander are lost",
                ((StudentPaymentResponse) actualHandledExceptionResult.getBody()).message());
        assertEquals(HttpStatus.NOT_FOUND,
                ((StudentPaymentResponse) actualHandledExceptionResult.getBody()).httpStatus());
        verify(exception).getMessage();
    }

    /**
     * Method under test: {@link RestControllerExceptionAdvice#handleHttpExceptions(Exception)}
     */
    @Test
    void testHandleHttpExceptions() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        RestControllerExceptionAdvice restControllerExceptionAdvice = new RestControllerExceptionAdvice();
        ResponseEntity<Object> actualHandleHttpExceptionsResult = restControllerExceptionAdvice
                .handleHttpExceptions(new Exception("foo"));
        assertTrue(actualHandleHttpExceptionsResult.hasBody());
        assertEquals(400, actualHandleHttpExceptionsResult.getStatusCodeValue());
        assertTrue(actualHandleHttpExceptionsResult.getHeaders().isEmpty());
        assertNull(((StudentPaymentResponse) actualHandleHttpExceptionsResult.getBody()).payload());
        assertEquals("foo", ((StudentPaymentResponse) actualHandleHttpExceptionsResult.getBody()).message());
        assertEquals(HttpStatus.BAD_REQUEST,
                ((StudentPaymentResponse) actualHandleHttpExceptionsResult.getBody()).httpStatus());
    }

    /**
     * Method under test: {@link RestControllerExceptionAdvice#handleHttpExceptions(Exception)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleHttpExceptions2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Exception.getMessage()" because "exception" is null
        //       at com.pesapap.apiv1.api.RestControllerExceptionAdvice.handleHttpExceptions(RestControllerExceptionAdvice.java:53)
        //   See https://diff.blue/R013 to resolve this issue.

        (new RestControllerExceptionAdvice()).handleHttpExceptions(null);
    }
}

