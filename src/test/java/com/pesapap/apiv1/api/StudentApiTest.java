package com.pesapap.apiv1.api;

import com.pesapap.apiv1.dto.PaymentRequest;
import com.pesapap.apiv1.dto.StudentPaymentResponse;
import com.pesapap.apiv1.dto.StudentValidationResponse;
import com.pesapap.apiv1.models.Student;
import com.pesapap.apiv1.repo.StudentRepo;
import com.pesapap.apiv1.services.StudentService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class StudentApiTest {
    private StudentRepo studentRepo;

    @BeforeEach
    public void setUp() {
        studentRepo = mock(StudentRepo.class);
    }

    @Test
    void testAddStudent() {
        Student student = new Student();
        when(studentRepo.findByRegistrationId(Mockito.any())).thenReturn(Optional.of(student));
        StudentApi studentApi = new StudentApi(new StudentService(studentRepo));
        ResponseEntity<StudentPaymentResponse> actualAddStudentResult = studentApi.addStudent(new Student());
        assertTrue(actualAddStudentResult.hasBody());
        assertTrue(actualAddStudentResult.getHeaders().isEmpty());
        assertEquals(208, actualAddStudentResult.getStatusCodeValue());
        StudentPaymentResponse body = actualAddStudentResult.getBody();
        assertEquals("Student already exists", body.message());
        assertEquals(HttpStatus.ALREADY_REPORTED, body.httpStatus());
        assertSame(student, body.payload());
        verify(studentRepo).findByRegistrationId(Mockito.any());
    }

    @Test
    void testFindStudentById() {
        StudentRepo studentRepo = mock(StudentRepo.class);
        Student student = new Student();
        when(studentRepo.findByRegistrationId(Mockito.any())).thenReturn(Optional.of(student));
        ResponseEntity<StudentValidationResponse> actualFindStudentByIdResult = (new StudentApi(
                new StudentService(studentRepo))).findStudentById("42");
        assertTrue(actualFindStudentByIdResult.hasBody());
        assertTrue(actualFindStudentByIdResult.getHeaders().isEmpty());
        assertEquals(200, actualFindStudentByIdResult.getStatusCodeValue());
        StudentValidationResponse body = actualFindStudentByIdResult.getBody();
        assertSame(student, body.payload());
        assertEquals("Success", body.message());
        assertEquals(HttpStatus.OK, body.status());
        verify(studentRepo).findByRegistrationId(Mockito.any());
    }

    @Test
    void testPaymentResponseReturnsPayload() {

        StudentService studentService = mock(StudentService.class);
        when(studentService.paymentResponse(Mockito.any()))
                .thenReturn(new StudentPaymentResponse("Payload", "Success", HttpStatus.CONTINUE));
        StudentApi studentApi = new StudentApi(studentService);
        ResponseEntity<StudentPaymentResponse> actualPaymentResponseResult = studentApi
                .paymentResponse(new PaymentRequest(null));
        assertTrue(actualPaymentResponseResult.hasBody());
        assertTrue(actualPaymentResponseResult.getHeaders().isEmpty());
        assertEquals(100, actualPaymentResponseResult.getStatusCodeValue());
        verify(studentService).paymentResponse(Mockito.any());
    }

}

