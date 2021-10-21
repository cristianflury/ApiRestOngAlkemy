package org.alkemy.somosmas.controller;

import io.jsonwebtoken.ExpiredJwtException;
import org.alkemy.somosmas.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<ApiError> handleBadCredentialsException(BadCredentialsException e) {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;

        ApiError apiError = new ApiError(
                e.getMessage(),
                httpStatus,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiError, httpStatus);
    }

    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<ApiError> handleJwtExpiredException(Exception e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ApiError apiError = new ApiError(
                e.getMessage(),
                httpStatus,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiError, httpStatus);
    }


    @ExceptionHandler(value = {EmailUnavailableException.class})
    public ResponseEntity<ApiError> handleEmailUnavailableException(EmailUnavailableException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiError apiError = new ApiError(
                "Error: email ya registrado",  httpStatus, LocalDateTime.now()
        );
        return new ResponseEntity<>(apiError, httpStatus);
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<ApiError> handleNoSuchElementException(NoSuchElementException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiError apiError = new ApiError(
                "Error: entidad no encontrada.", httpStatus, LocalDateTime.now()
        );
        return new ResponseEntity<>(apiError, httpStatus);
    }

    @ExceptionHandler(value = {NotAuthorizedUserException.class})
    public ResponseEntity<ApiError> handleNotAuthorizedUserException(NotAuthorizedUserException e) {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        ApiError apiError = new ApiError("You have no authority", httpStatus, LocalDateTime.now());
        return new ResponseEntity<>(apiError, httpStatus);
    }

    @ExceptionHandler(value = {NoSuchFileException.class})
    public ResponseEntity<ApiError> handleNoSuchFileException(NoSuchFileException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiError apiError = new ApiError(
                "La imagen indicada no existe en aws",httpStatus, LocalDateTime.now()
        );
        return new ResponseEntity<>(apiError, httpStatus);
    }

    @ExceptionHandler(value = {NewsNotFoundException.class})
    public ResponseEntity<ApiError> handleNewsNotFoundException(NewsNotFoundException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ApiError apiError = new ApiError(
                e.getMessage(),
                httpStatus,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiError, httpStatus);
    }


    @ExceptionHandler(value ={CategoryNotFoundException.class})
    public ResponseEntity<ApiError> handleCategoryNotFoundException(CategoryNotFoundException e){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ApiError apiError = new ApiError(
                "Category not found",
                httpStatus,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiError, httpStatus);
    }


    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> errorList = bindingResult.getAllErrors();
        StringBuilder errorMessage = new StringBuilder();
        for (ObjectError objectError : errorList) {
             errorMessage.append(objectError.getDefaultMessage()).append(". ");
        }
        ApiError apiError = new ApiError(
                errorMessage.toString(),
                status,
                LocalDateTime.now());
        return new ResponseEntity<>(apiError, status);
    }
}
