package com.pesapap.apiv1.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@JsonSerialize
public record StudentPaymentResponse(Object payload,String message, HttpStatus httpStatus) {

}


