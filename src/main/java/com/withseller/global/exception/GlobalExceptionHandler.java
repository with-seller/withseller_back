package com.withseller.global.exception;

import com.withseller.global.exception.dto.ErrorResponse;
import com.withseller.global.exception.user.NotFoundUserException;
import com.withseller.global.exception.user.NotMatchPasswordException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.withseller.global.exception.dto.ErrorResponseCode.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /* Dto validation failed exception */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestField(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append(fieldError.getDefaultMessage());
        }

        ErrorResponse errorResponse = new ErrorResponse(builder.toString());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    /*  */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorResponse> constraintViolationException() {
        log.error("ConstraintViolationException throw Exception : {}", DUPLICATED_KEY);
        ErrorResponse errorResponse = new ErrorResponse(DUPLICATED_KEY.getMessage());
        return ResponseEntity.status(DUPLICATED_KEY.getStatus()).body(errorResponse);
    }

    /*  */
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<ErrorResponse> dataIntegrityViolationException() {
        log.error("DataIntegrityViolationException throw Exception : {}", DATA_INTEGRITY_VIOLATION);
        ErrorResponse errorResponse = new ErrorResponse(DATA_INTEGRITY_VIOLATION.getMessage());
        return ResponseEntity.status(DATA_INTEGRITY_VIOLATION.getStatus()).body(errorResponse);
    }

    /* User not found exception */
    @ExceptionHandler(NotFoundUserException.class)
    protected ResponseEntity<ErrorResponse> notFoundUserException(RuntimeException e) {
        log.error("RuntimeException throw notFoundUserException : {}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(NOT_FOUND.getStatus()).body(errorResponse);
    }

    /* User password no match exception */
    @ExceptionHandler(value = { NotMatchPasswordException.class })
    protected ResponseEntity<ErrorResponse> notMatchPasswordException(RuntimeException e) {
        log.error("RuntimeException throw notMatchPasswordException : {}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(NOT_FOUND.getStatus()).body(errorResponse);
    }
}
