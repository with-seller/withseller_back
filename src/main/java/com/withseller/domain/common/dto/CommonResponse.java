package com.withseller.domain.common.dto;

import com.withseller.exception.ErrorCode;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class CommonResponse {
    private String status;
    private String message;

    public static ResponseEntity<CommonResponse> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getStatus())
                .body(CommonResponse.builder()
                        .status("FAIL")
                        .message(errorCode.getMessage())
                        .build());
    }

    public static ResponseEntity<CommonResponse> toResponseEntity(HttpStatus httpStatus, String message) {
        return ResponseEntity.status(httpStatus)
                .body(CommonResponse.builder()
                        .status("FAIL")
                        .message(message)
                        .build());
    }
}
