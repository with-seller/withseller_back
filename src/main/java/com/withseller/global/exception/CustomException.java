package com.withseller.global.exception;

import com.withseller.global.exception.dto.ErrorResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {
    private final ErrorResponseCode errorResponseCode;
}
