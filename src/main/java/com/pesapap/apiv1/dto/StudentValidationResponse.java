package com.pesapap.apiv1.dto;

import org.springframework.http.HttpStatus;


public record StudentValidationResponse(Object payload,
                                        String message,
                                        HttpStatus status) {
}
