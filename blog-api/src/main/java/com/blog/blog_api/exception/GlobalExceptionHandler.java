package com.blog.blog_api.exception;


import com.blog.blog_api.DTO.response.ApiResponse;
import org.hibernate.boot.beanvalidation.DuplicationStrategyImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(
            ResourceNotFoundException ex ) {
        return new ResponseEntity<>(
                ApiResponse.error(ex.getMessage()), HttpStatus.NOT_FOUND
        );
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadRequest (
            BadRequestException ex) {
        return new ResponseEntity<>(
                ApiResponse.error(ex.getMessage()),HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse<Void>> handleDuplicate(
            DuplicateResourceException ex) {
        return new ResponseEntity<>(
                ApiResponse.error(ex.getMessage()), HttpStatus.CONFLICT
        );
    }
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiResponse<Map<String, String>>>
        handleValidation(MethodArgumentNotValidException ex) {

            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach(error -> {
                String fieldName = ((FieldError) error).getField();
                String message = error.getDefaultMessage();
                errors.put(fieldName, message);
            });

            ApiResponse<Map<String, String>> response = ApiResponse
                    .<Map<String, String>>builder()
                    .success(false)
                    .message("Validation failed")
                    .data(errors)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiResponse<Void>> handleGeneral (Exception ex){
            return new ResponseEntity<>(
                    ApiResponse.error("Something went wrong: " + ex.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
