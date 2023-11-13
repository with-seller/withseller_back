package com.withseller.exception;

import com.withseller.domain.common.dto.CommonResponse;
import com.withseller.exception.user.NotFoundUserException;
import com.withseller.exception.user.NotMatchPasswordException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.withseller.exception.ErrorCode.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append(fieldError.getDefaultMessage());
        }

        String message = builder.toString();
        log.error("MethodArgumentNotValidException throw Exception : {}", message);

        CommonResponse commonResponse = CommonResponse.builder()
                .status("FAIL")
                .message(message)
                .build();

        return ResponseEntity.status(status.value()).body(commonResponse);
    }

    @ExceptionHandler(value = { ConstraintViolationException.class })
    protected ResponseEntity<CommonResponse> constraintViolationException() {
        log.error("ConstraintViolationException throw Exception : {}", DUPLICATED_KEY);
        return CommonResponse.toResponseEntity(DUPLICATED_KEY);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<CommonResponse> dataIntegrityViolationException() {
        log.error("DataIntegrityViolationException throw Exception : {}", DATA_INTEGRITY_VIOLATION);
        return CommonResponse.toResponseEntity(DATA_INTEGRITY_VIOLATION);
    }

    @ExceptionHandler(value = { NotFoundUserException.class })
    protected ResponseEntity<CommonResponse> notFoundUserException(RuntimeException e) {
        log.error("RuntimeException throw notFoundUserException : {}", e.getMessage());
        return CommonResponse.toResponseEntity(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(value = { NotMatchPasswordException.class })
    protected ResponseEntity<CommonResponse> notMatchPasswordException(RuntimeException e) {
        log.error("RuntimeException throw notMatchPasswordException : {}", e.getMessage());
        return CommonResponse.toResponseEntity(HttpStatus.NOT_FOUND, e.getMessage());
    }
}
