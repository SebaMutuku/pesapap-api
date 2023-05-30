package com.pesapap.apiv1.api;

import com.pesapap.apiv1.dto.PaymentRequest;
import com.pesapap.apiv1.dto.StudentPaymentResponse;
import com.pesapap.apiv1.dto.StudentValidationResponse;
import com.pesapap.apiv1.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pesapap/v1/students", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class StudentApi {
    private final StudentService studentService;


    public StudentApi(@Autowired StudentService studentService) {
        this.studentService = studentService;

    }

    @GetMapping(value = "/getStudent/{registrationId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<StudentValidationResponse> findStudentById(@PathVariable String registrationId) {
        ResponseEntity<StudentValidationResponse> response = ResponseEntity.ok(studentService.findById(registrationId));
        log.info("Response object::: {}", response);
        return response;
    }

    @PutMapping(value = "/payment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentPaymentResponse> paymentResponse(@RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.ok(studentService.paymentResponse(paymentRequest));
    }
}
