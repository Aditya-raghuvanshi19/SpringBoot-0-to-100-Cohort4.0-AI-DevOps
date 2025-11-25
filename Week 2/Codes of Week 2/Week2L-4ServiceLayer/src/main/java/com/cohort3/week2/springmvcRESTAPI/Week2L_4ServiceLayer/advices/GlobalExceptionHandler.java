package com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.advices;

import com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //all the NoSuchElementException in the whole application is handle by this ExceptionHandler as it will catch that exception and pass into the function
//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> handleResourceNotFound(NoSuchElementException exception){
//        return new ResponseEntity<>("Resource not found in DB" , HttpStatus.NOT_FOUND);
//    }

//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<ApiError> handleResourceNotFound(NoSuchElementException exception){
//       ApiError apiError = ApiError.builder().status(HttpStatus.NOT_FOUND).message("Resource not found").build();
//       return new ResponseEntity<>( apiError,HttpStatus.NOT_FOUND);
//    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException exception){
//        ApiError apiError = ApiError.builder()
//                .status(HttpStatus.NOT_FOUND)
//                .message(exception.getMessage())
//                .build();
//        return new ResponseEntity<>( apiError,HttpStatus.NOT_FOUND);
//    }
@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException exception){
    ApiError apiError = ApiError.builder()
            .status(HttpStatus.NOT_FOUND)
            .message(exception.getMessage())
            .build();
    return buildErrorResponseEntity(apiError);
}



//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiError> handleInternalServerError(Exception e){
//        ApiError apiError = ApiError.builder()
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .message(e.getMessage())
//                .build();
//        return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
//    }
@ExceptionHandler(Exception.class)
public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception e){
    ApiError apiError = ApiError.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .message(e.getMessage())
            .build();
    return buildErrorResponseEntity(apiError);
}
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiError> handleInputValidationErrors(MethodArgumentNotValidException e){ //from this exception it combines all the exception by the binding result and give one list of all errors
//
//        List<String> errors = e.getBindingResult()
//                .getAllErrors()
//                .stream()
//                .map(error -> error.getDefaultMessage())
//                .collect(Collectors.toList());
//
//        ApiError apiError = ApiError.builder()
//                .status(HttpStatus.BAD_REQUEST)
//                .message("Input validation failed")
//                .subErrors(errors)
//                .build();
//        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
//    }
    //the above is to handle the checks apply on the DTOs to take the input
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ApiResponse<?>> handleInputValidationErrors(MethodArgumentNotValidException e){ //from this exception it combines all the exception by the binding result and give one list of all errors

    List<String> errors = e.getBindingResult()
            .getAllErrors()
            .stream()
            .map(error -> error.getDefaultMessage())
            .collect(Collectors.toList());

    ApiError apiError = ApiError.builder()
            .status(HttpStatus.BAD_REQUEST)
            .message("Input validation failed")
            .subErrors(errors)
            .build();
    return buildErrorResponseEntity(apiError);
}
    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError) , apiError.getStatus());
    }
}
