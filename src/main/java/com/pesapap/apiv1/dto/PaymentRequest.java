package com.pesapap.apiv1.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.ToString;

@JsonSerialize
public record PaymentRequest(StudentDTO payload) {
}


