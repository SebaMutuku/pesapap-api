package com.pesapap.apiv1.services;

import com.pesapap.apiv1.dto.PaymentChannels;
import com.pesapap.apiv1.dto.PaymentRequest;
import com.pesapap.apiv1.dto.StudentDTO;
import com.pesapap.apiv1.dto.StudentPaymentResponse;
import com.pesapap.apiv1.dto.StudentValidationResponse;
import com.pesapap.apiv1.models.Student;
import com.pesapap.apiv1.repo.StudentRepo;
import com.pesapap.apiv1.serviceimpl.AbstractStudentService;
import java.util.Date;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class StudentService extends AbstractStudentService {
    private final StudentRepo studentRepo;

    public StudentService(@Autowired StudentRepo studentRepo) {
        this.studentRepo = studentRepo;

    }

    @Override
    @Transactional
    public StudentPaymentResponse createStudent(Student student) {
        log.info("Create student request ----> [{}]", student);
        Optional<Student> dbStudent = studentRepo.findByRegistrationId(student.getRegistrationId());
        return dbStudent.map(existingStudent ->
                        new StudentPaymentResponse(existingStudent, "Student already exists", HttpStatus.ALREADY_REPORTED))
                .orElseGet(() -> {
                    StudentPaymentResponse studentPaymentResponse;
                    if (student.getRegistrationId() != null && student.getFullName() != null && student.getCourseName() != null) {
                        Student sessionStudent = Student.builder()
                                .courseName(student.getCourseName())
                                .fullName(student.getFullName())
                                .feeBalance(0.0)
                                .annualFee(87000.00)
                                .paidFees(0.00)
                                .paymentChannel(null)
                                .registrationId(student.getRegistrationId())
                                .joiningDate(new Date())
                                .build();
                        studentRepo.save(sessionStudent);
                        studentPaymentResponse = new StudentPaymentResponse(sessionStudent, "Success", HttpStatus.CREATED);
                        log.info("Student response ---> [{}]", studentPaymentResponse);
                        return studentPaymentResponse;
                    }
                    studentPaymentResponse = new StudentPaymentResponse(null, "Invalid Student data", HttpStatus.BAD_REQUEST);
                    log.error("Student response ---> [{}]", studentPaymentResponse);
                    return studentPaymentResponse;
                });
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @Override
    public StudentValidationResponse findById(String registrationId) {
        log.info("Registration id ---> [{}]", registrationId);
        Optional<StudentValidationResponse> studentValidationResponse = studentRepo.findByRegistrationId(registrationId.trim())
                .map(student -> new StudentValidationResponse(student, "Success", HttpStatus.OK));
        studentValidationResponse.ifPresent(response -> log.info("Validation response ----> [{}]", response));
        return studentValidationResponse.get();

    }

    @Transactional
    @Override
    public StudentPaymentResponse paymentResponse(PaymentRequest paymentRequest) {
        log.info("Fee payment request ---> [{}]", paymentRequest);
        StudentDTO paymentInfo = paymentRequest.payload();
        Optional<StudentPaymentResponse> studentPaymentResponse = studentRepo.findByRegistrationId(paymentRequest.payload().getRegNumber())
                .map(student -> {
                    student.setPaidFees(paymentInfo.getPaymentAmount());
                    student.setPaymentChannel(PaymentChannels.valueOf(paymentInfo.getPaymentChannel()).name());
                    student.setFeeBalance(student.getAnnualFee() - paymentInfo.getPaymentAmount());
                    Student savedStudent = studentRepo.save(student);
                    return new StudentPaymentResponse(savedStudent, "Success", HttpStatus.OK);
                });
        studentPaymentResponse.ifPresent(response -> log.info("Response body when creating a student ----> [{}]", response));
        return studentPaymentResponse.get();
    }
}
