package com.pesapap.apiv1.services;

import com.pesapap.apiv1.dto.PaymentChannels;
import com.pesapap.apiv1.dto.PaymentRequest;
import com.pesapap.apiv1.dto.StudentDTO;
import com.pesapap.apiv1.dto.StudentPaymentResponse;
import com.pesapap.apiv1.dto.StudentValidationResponse;
import com.pesapap.apiv1.models.Student;
import com.pesapap.apiv1.repo.StudentRepo;
import com.pesapap.apiv1.serviceimpl.StudentServiceImpl;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class StudentService extends StudentServiceImpl {
    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;

    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @Override
    public StudentValidationResponse findById(String registrationId) {
        try {
            Optional<StudentValidationResponse> studentValidationResponse = studentRepo.findByRegistrationId(registrationId)
                    .map(student -> new StudentValidationResponse(student, "Success", HttpStatus.OK));
            studentValidationResponse.ifPresent(response -> log.error("Student object----->[{}]", response));
            return studentValidationResponse.orElse(new StudentValidationResponse(null, "Student with that registration not found", HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            StudentValidationResponse studentValidationResponse = new StudentValidationResponse(
                    null,
                    e.getMessage(),
                    HttpStatus.NOT_FOUND);
            log.error("Response object ", e);
            return studentValidationResponse;
        }
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @Override
    public StudentPaymentResponse paymentResponse(PaymentRequest paymentRequest) {
        try {
            log.info("Request [{}]", paymentRequest);
            StudentDTO paymentInfo = paymentRequest.payload();
            Optional<StudentPaymentResponse> studentPaymentResponse = studentRepo.findByRegistrationId(paymentRequest.payload().getRegNumber())
                    .map(student -> {
                        student.setPaymentChannel(paymentInfo.getPaymentChannel());
                        student.setPaidFees(paymentInfo.getPaymentAmount());
                        student.setPaymentChannel(PaymentChannels.valueOf(paymentInfo.getPaymentChannel()).name());
                        student.setFeeBalance(student.getAnnualFee() - paymentInfo.getPaymentAmount());
                        studentRepo.save(student);
                        return new StudentPaymentResponse(student, "Success", HttpStatus.OK);
                    });
            studentPaymentResponse.ifPresent(response -> log.error("Student object:: [{}]", response));
            return studentPaymentResponse.orElse(new StudentPaymentResponse(null, "An Error occurred", HttpStatus.EXPECTATION_FAILED));
        } catch (Exception exception) {
            StudentPaymentResponse studentPaymentResponse = new StudentPaymentResponse(null, exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
            log.error("Response  object ---> ", exception);
            return studentPaymentResponse;
        }
    }

    @Override
    public StudentPaymentResponse createStudent(Student student) {
        Optional<Student> dbStudent = studentRepo.findByRegistrationId(student.getRegistrationId());
        return dbStudent.map(existingStudent ->
                        new StudentPaymentResponse(existingStudent, "Student already exists", HttpStatus.ALREADY_REPORTED))
                .orElseGet(() -> {
                    Student sessionStudent = Student.builder()
                            .courseName(student.getCourseName())
                            .fullName(student.getFullName())
                            .feeBalance(0.0)
                            .annualFee(87000.00)
                            .paidFees(0.00)
                            .paymentChannel(null)
                            .registrationId(student.getRegistrationId())
                            .build();
                    studentRepo.save(sessionStudent);

                    StudentPaymentResponse studentPaymentResponse = new StudentPaymentResponse(sessionStudent, "Success", HttpStatus.CREATED);
                    log.info("Response [{}]", studentPaymentResponse);
                    return studentPaymentResponse;
                });
    }
}
