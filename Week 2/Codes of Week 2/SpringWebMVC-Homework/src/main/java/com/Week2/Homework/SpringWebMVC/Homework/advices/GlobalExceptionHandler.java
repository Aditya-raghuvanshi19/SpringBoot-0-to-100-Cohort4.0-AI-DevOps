package com.Week2.Homework.SpringWebMVC.Homework.advices;

import com.Week2.Homework.SpringWebMVC.Homework.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleInputValidationError (MethodArgumentNotValidException e){
        List<String> errors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .error("Input validation failed")
                .subError(errors)
                .build();
        return getResponseEntity(apiError);
    }

   @ExceptionHandler(ResourceNotFoundException.class)
   public ResponseEntity<ApiResponse<?>> resourceNotFoundHandle (ResourceNotFoundException exception){
        ApiError apiError = ApiError.builder().status(HttpStatus.NOT_FOUND)
                .error(exception.getMessage()).build();
        return getResponseEntity(apiError);
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception exception){
        ApiError apiError= ApiError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).error("Internal Sever Error found")
                .build();
        return getResponseEntity(apiError);
   }

    private ResponseEntity<ApiResponse<?>> getResponseEntity(ApiError apiError){
       return  new ResponseEntity<>(new ApiResponse<>(apiError) , apiError.getStatus());
    }

}
