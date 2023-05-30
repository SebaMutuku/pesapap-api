package com.pesapap.apiv1.services;

import com.pesapap.apiv1.dto.PaymentRequest;
import com.pesapap.apiv1.dto.StudentDTO;
import com.pesapap.apiv1.dto.StudentPaymentResponse;
import com.pesapap.apiv1.dto.StudentValidationResponse;
import com.pesapap.apiv1.models.Student;
import com.pesapap.apiv1.repo.StudentRepo;
import com.pesapap.apiv1.serviceimpl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService extends StudentServiceImpl {
    private final StudentRepo studentRepo;

    public StudentService(@Autowired StudentRepo studentRepo) {
        this.studentRepo = studentRepo;

    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @Override
    public StudentValidationResponse findById(String registrationId) {
        try {
            return new StudentValidationResponse(studentRepo.findByRegistrationId(registrationId), "Success", HttpStatus.OK);
        } catch (Exception e) {
            return new StudentValidationResponse(
                    null,
                    "Student with that registration not found",
                    HttpStatus.NOT_FOUND


            );
        }
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @Override
    public StudentPaymentResponse paymentResponse(PaymentRequest paymentRequest) {
        try {
            var paymentInfo = paymentRequest.payload();
            var student = studentRepo.findByRegistrationId(paymentInfo.getRegNumber());
            student.setPaymentChannel(paymentInfo.getPaymentChannel());
            student.setPaidFees(paymentInfo.getPaymentAmount());
            student.setPaymentChannel(paymentInfo.getPaymentChannel());
            student.setFeeBalance(student.getAnnualFee() - paymentInfo.getPaymentAmount());
            studentRepo.save(Student.builder().build());
            var responseDTO = StudentDTO.builder().paymentChannel(paymentInfo.getPaymentChannel()).paymentAmount(paymentInfo.getPaymentAmount()).paymentAmount(paymentInfo.getPaymentAmount()).build();
            return new StudentPaymentResponse(responseDTO, "Success", HttpStatus.OK);
        } catch (Exception exception) {
            return new StudentPaymentResponse(null, "An error occurred", HttpStatus.EXPECTATION_FAILED);
        }
    }
}
