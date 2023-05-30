package com.pesapap.apiv1.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StudentDTO {
    Double paymentAmount;
    String paymentChannel;
    String regNumber;

}
