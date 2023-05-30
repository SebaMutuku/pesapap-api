package com.pesapap.apiv1.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@JsonSerialize
public record StudentValidationResponse(Object payload,
                                        String message,
                                        HttpStatus status) {
}
