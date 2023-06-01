package com.pesapap.apiv1.serviceimpl;

import com.pesapap.apiv1.dto.PaymentRequest;
import com.pesapap.apiv1.dto.StudentPaymentResponse;
import com.pesapap.apiv1.dto.StudentValidationResponse;
import com.pesapap.apiv1.models.Student;

public abstract class AbstractStudentService {
    public abstract StudentPaymentResponse createStudent(Student student);

    public abstract StudentValidationResponse findById(String registrationId);

    public abstract StudentPaymentResponse paymentResponse(PaymentRequest paymentRequest);


}
