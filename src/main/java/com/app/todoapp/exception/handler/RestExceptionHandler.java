package com.app.todoapp.exception.handler;


import com.app.todoapp.exception.models.IdNotFoundException;
import com.app.todoapp.models.response.http.BadRequestResponse;
import com.app.todoapp.models.response.http.InternalServerResponse;
import com.app.todoapp.models.response.http.UnAuthorizedResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {


        // get all attributes names that failed in validation
        Set<String> failedAttributesNamesInValidation = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField())
                .collect(Collectors.toSet());


        // create an empty map
        Map<String , List<String>> errorsAndCauses = new HashMap<>();

        // fill map with Key = attribute name that failed in validation , Value = List of causes that this attribute failed in
        failedAttributesNamesInValidation.forEach(error -> {
            errorsAndCauses.put(error , new ArrayList<>());
        });


        // add each validation cause for each attribute in the validation list
        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> {
                    errorsAndCauses.get(fieldError.getField()).add(fieldError.getDefaultMessage());
                });


        return new ResponseEntity<>(
                BadRequestResponse.builder()
                        .message("Validation Failed")
                        .failed_validation_attributes(errorsAndCauses)
                        .build()
                , HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<?> handleGeneralException(Exception exception) {
        return new ResponseEntity<>(
                InternalServerResponse.builder()
                        .message(exception.getMessage())
                        .build()
                , HttpStatus.INTERNAL_SERVER_ERROR
        );
    }


    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<?> handleAuthenticationException(Exception exception) {
        return new ResponseEntity<>(
                UnAuthorizedResponse.builder()
                        .message("Wrong Username/Email or Password")
                        .build()
                , HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler({ IdNotFoundException.class })
    public ResponseEntity<?> handleIdNotFoundException(IdNotFoundException exception) {
        return new ResponseEntity<>(
                InternalServerResponse.builder()
                        .message(exception.getMessage())
                        .build()
                , HttpStatus.NOT_FOUND
        );
    }

}
