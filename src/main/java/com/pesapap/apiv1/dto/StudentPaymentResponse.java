package com.pesapap.apiv1.dto;

import org.springframework.http.HttpStatus;


public record StudentPaymentResponse(Object payload, String message, HttpStatus httpStatus) {

}


