package com.pesapap.apiv1.services;

import com.pesapap.apiv1.dto.PaymentRequest;
import com.pesapap.apiv1.dto.StudentPaymentResponse;
import com.pesapap.apiv1.dto.StudentValidationResponse;
import com.pesapap.apiv1.models.Student;
import com.pesapap.apiv1.repo.StudentRepo;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {StudentService.class})
@ExtendWith(SpringExtension.class)
class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    private StudentRepo studentRepo;

    @BeforeEach
    public void setUp() {
        studentRepo = mock(StudentRepo.class);
        when(studentRepo.save(Mockito.any())).thenReturn(new Student());
    }

    @Test
    void testSuccessfullyCreatesStudent() {
        when(studentRepo.findByRegistrationId(Mockito.any())).thenReturn(Optional.empty());
        StudentService studentService = new StudentService(studentRepo);
        Student student = mock(Student.class);
        when(student.getCourseName()).thenReturn("Course Name");
        when(student.getFullName()).thenReturn("Dr Jane Doe");
        when(student.getRegistrationId()).thenReturn("42");
        StudentPaymentResponse actualCreateStudentResult = studentService.createStudent(student);
        assertEquals(HttpStatus.CREATED, actualCreateStudentResult.httpStatus());
        Object payloadResult = actualCreateStudentResult.payload();
        assertTrue(payloadResult instanceof Student);
        assertEquals("Success", actualCreateStudentResult.message());
        assertEquals(87000.0d, ((Student) payloadResult).getAnnualFee().doubleValue());
        assertEquals("42", ((Student) payloadResult).getRegistrationId());
        assertNull(((Student) payloadResult).getPaymentChannel());
        assertEquals(0.0d, ((Student) payloadResult).getPaidFees().doubleValue());
        assertNull(((Student) payloadResult).getId());
        assertEquals("Dr Jane Doe", ((Student) payloadResult).getFullName());
        assertEquals(0.0d, ((Student) payloadResult).getFeeBalance().doubleValue());
        assertEquals("Course Name", ((Student) payloadResult).getCourseName());
        verify(studentRepo).save(Mockito.any());
        verify(studentRepo).findByRegistrationId(Mockito.any());
        verify(student, atLeast(1)).getCourseName();
        verify(student, atLeast(1)).getFullName();
        verify(student, atLeast(1)).getRegistrationId();
    }

    @Test
    void testCreateStudentInvalidData() {
        when(studentRepo.findByRegistrationId(Mockito.any())).thenReturn(Optional.empty());
        StudentService studentService = new StudentService(studentRepo);
        Student student = mock(Student.class);
        when(student.getCourseName()).thenReturn("Course Name");
        when(student.getFullName()).thenReturn(null);
        when(student.getRegistrationId()).thenReturn("42");
        StudentPaymentResponse actualCreateStudentResult = studentService.createStudent(student);
        assertEquals(HttpStatus.BAD_REQUEST, actualCreateStudentResult.httpStatus());
        assertNull(actualCreateStudentResult.payload());
        assertEquals("Invalid Student data", actualCreateStudentResult.message());
        verify(studentRepo).findByRegistrationId(Mockito.any());
        verify(student).getFullName();
        verify(student, atLeast(1)).getRegistrationId();
    }

    @Test
    void testFindByIdReturnsStudent() {
        Student student = new Student();
        when(studentRepo.findByRegistrationId(Mockito.any())).thenReturn(Optional.of(student));
        StudentValidationResponse actualFindByIdResult = (new StudentService(studentRepo)).findById("42");
        assertEquals("Success", actualFindByIdResult.message());
        assertEquals(HttpStatus.OK, actualFindByIdResult.status());
        assertSame(student, actualFindByIdResult.payload());
        verify(studentRepo).findByRegistrationId(Mockito.any());
    }

    @Test
    void testFindByIdReturnsNotFound() {
        when(studentRepo.findByRegistrationId(Mockito.any())).thenReturn(Optional.empty());
        (new StudentService(studentRepo)).findById("42");
    }


    @Test
    void testPaymentResponse() {
        PaymentRequest paymentRequest = new PaymentRequest(null);
        StudentValidationResponse actualFindByIdResult = (new StudentService(studentRepo)).findById("42");
        assertEquals("Success", actualFindByIdResult.message());
        assertEquals(HttpStatus.OK, actualFindByIdResult.status());
        verify(studentRepo).findByRegistrationId(Mockito.any());
        studentService.paymentResponse(paymentRequest);
    }
}

