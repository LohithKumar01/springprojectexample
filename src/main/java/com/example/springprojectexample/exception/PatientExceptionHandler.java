package com.example.springprojectexample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PatientExceptionHandler {

    @ExceptionHandler(PatientException.class)
    public ResponseEntity<?> handlePatientException(PatientException e){
        return null;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception e){
        //This will catch any other exceptions which are not handled by specific exception handlers
        return new ResponseEntity<>(new PatientError("Patient Details Error",99999L), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
