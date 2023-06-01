package com.pesapap.apiv1.api;

import com.pesapap.apiv1.dto.PaymentRequest;
import com.pesapap.apiv1.dto.StudentPaymentResponse;
import com.pesapap.apiv1.dto.StudentValidationResponse;
import com.pesapap.apiv1.models.Student;
import com.pesapap.apiv1.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pesapap/v1/students", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class StudentApi {
    private final StudentService studentService;


    public StudentApi(@Autowired StudentService studentService) {
        this.studentService = studentService;

    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentPaymentResponse> addStudent(@RequestBody Student student) {
        StudentPaymentResponse body = studentService.createStudent(student);
        log.info("Create student response --> [{}]", body);
        return new ResponseEntity<>(body, body.httpStatus());
    }

    @GetMapping(value = "/getStudent")
    @ResponseBody
    public ResponseEntity<StudentValidationResponse> findStudentById(@RequestParam String registrationId) {
        StudentValidationResponse body = studentService.findById(registrationId);
        log.info("Validation response --> [{}]", body);
        return new ResponseEntity<>(body, body.status());
    }

    @PutMapping(value = "/payment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentPaymentResponse> paymentResponse(@RequestBody PaymentRequest paymentRequest) {
        StudentPaymentResponse body = studentService.paymentResponse(paymentRequest);
        log.info("Payment response --> [{}]", body);
        return new ResponseEntity<>(body, body.httpStatus());
    }
}
