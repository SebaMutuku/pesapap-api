package com.pesapap.apiv1.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;

@JsonSerialize
public record StudentPaymentResponse(Object payload,String message, HttpStatus httpStatus) {

}


