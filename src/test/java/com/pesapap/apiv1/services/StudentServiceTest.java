package com.pesapap.apiv1.services;

import com.pesapap.apiv1.dto.PaymentRequest;
import com.pesapap.apiv1.dto.StudentPaymentResponse;
import com.pesapap.apiv1.dto.StudentValidationResponse;
import com.pesapap.apiv1.models.Student;
import com.pesapap.apiv1.repo.StudentRepo;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@SpringBootTest
class StudentServiceTest {

    StudentService studentService = mock(StudentService.class);

    @MockBean
    private StudentRepo studentRepo;

    @BeforeEach
    public void setUp() {
        when(studentRepo.save(Mockito.any())).thenReturn(new Student());
    }

    @Test
    public void testSuccessfullyCreatesStudent() {
        when(studentRepo.findByRegistrationId(Mockito.any())).thenReturn(Optional.empty());
        StudentService studentService = new StudentService(studentRepo);
        Student student = mock(Student.class);
        when(student.getCourseName()).thenReturn("Course Name");
        when(student.getFullName()).thenReturn("Student Tests");
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
        assertEquals(0.0d, ((Student) payloadResult).getFeeBalance().doubleValue());
        assertEquals("Course Name", ((Student) payloadResult).getCourseName());
        verify(studentRepo).save(Mockito.any());
        verify(studentRepo).findByRegistrationId(Mockito.any());
        verify(student, atLeast(1)).getCourseName();
        verify(student, atLeast(1)).getFullName();
        verify(student, atLeast(1)).getRegistrationId();
    }

    @Test
    public void testCreateStudentInvalidData() {
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
    public void testFindByIdReturnsStudent() {
        Student student = new Student();
        when(studentRepo.findByRegistrationId(Mockito.any())).thenReturn(Optional.of(student));
        StudentValidationResponse actualFindByIdResult = (new StudentService(studentRepo)).findById("42");
        assertEquals("Success", actualFindByIdResult.message());
        assertEquals(HttpStatus.OK, actualFindByIdResult.status());
        assertSame(student, actualFindByIdResult.payload());
        verify(studentRepo).findByRegistrationId(Mockito.any());
    }



    @Test
    public void testPaymentResponse() {
        when(studentService.findById("42")).thenThrow(new NoSuchElementException("No value present"));
        when(studentRepo.findByRegistrationId(any())).thenReturn(null);
        studentRepo.findByRegistrationId(any());
        StudentPaymentResponse response = new StudentPaymentResponse(null, "No value Present", HttpStatus.BAD_REQUEST);
        when(studentService.paymentResponse(new PaymentRequest(null))).thenReturn(response);
        verify(studentRepo).findByRegistrationId(Mockito.any());
        assertEquals(response.httpStatus(), HttpStatus.BAD_REQUEST);
    }
}

