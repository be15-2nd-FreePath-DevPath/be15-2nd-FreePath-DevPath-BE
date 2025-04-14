package com.freepath.devpath.common.exception;

import com.freepath.devpath.common.dto.ApiResponse;
import com.freepath.devpath.email.exception.NewsNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException e) {
        ErrorCode errorCode = ErrorCode.VALIDATION_ERROR;
        StringBuilder errorMessage = new StringBuilder(errorCode.getMessage());
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errorMessage.append(String.format(" [%s : %s]", error.getField(), error.getDefaultMessage()));
        }

        ApiResponse<Void> response
                = ApiResponse.failure(errorCode.getCode(), errorMessage.toString());
        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleException(RuntimeException e) {
        ErrorCode errorCode = ErrorCode.UNKNOWN_RUNTIME_ERROR;

        String detailedMessage = errorCode.getMessage() + " -> " + e.getMessage();

        ApiResponse<Void> response = ApiResponse.failure(errorCode.getCode(), detailedMessage);
        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        ErrorCode errorCode = ErrorCode.UNKNOWN_ERROR;

        String detailedMessage = errorCode.getMessage() + " -> " + e.getMessage();

        ApiResponse<Void> response = ApiResponse.failure(errorCode.getCode(), detailedMessage);
        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }


    @ExceptionHandler(NewsNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNewsNotFoundException(NewsNotFoundException e) {
        ErrorCode errorCode = e.getErrorCode();
        ApiResponse<Void> response = ApiResponse.failure(errorCode.getCode(), errorCode.getMessage());
        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

}
