package com.pesapap.apiv1.api;

import com.pesapap.apiv1.dto.StudentPaymentResponse;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;


class RestControllerExceptionAdviceTest {

    @Test
    void testHandledExceptionReturnsInternalServerError() {
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


    @Test
    void testHandledExceptionWithRuntimeException() {

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
    void testHandledExceptionWithNoSuchElementException() {

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

    @Test
    void testHandledExceptionWithAccessDeniedException() {
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

    @Test
    void testHandledExceptionWithNoHandlerFoundException() {

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
}
