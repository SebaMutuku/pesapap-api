package com.pesapap.apiv1.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class StudentDTO {
    Double paymentAmount;
    String paymentChannel;
    String regNumber;

}
