package com.example.springprojectexample.exception;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Getter
@Log4j2
public class ApplicationException extends RuntimeException{
    private String message;
    private Exception e;

    public ApplicationException(String message,Exception e){
        super(message);
        this.message=message;
        this.e=e;
        log.error("Application Exception: {}", message, e);
    }
}
