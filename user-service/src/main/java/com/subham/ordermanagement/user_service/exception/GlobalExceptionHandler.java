package com.subham.ordermanagement.user_service.exception;

import com.subham.ordermanagement.user_service.dto.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError>
    handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request){
         ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(),"RESOURCE_NOT_FOUND",
                 ex.getMessage(),request.getRequestURI());

         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex,HttpServletRequest request){
       ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
               "INTERNAL_SERVER_ERROR",
               ex.getMessage(), request.getRequestURI());

       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
