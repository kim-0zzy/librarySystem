package com.example.libraryProject.exhandler;

import com.example.libraryProject.Exception.*;
import com.example.libraryProject.exhandler.form.ErrorForm;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.example.libraryProject.Controller")
public class ApiControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResultListIsEmptyException.class)
    public ErrorForm resultListIsEmptyExHandler(ResultListIsEmptyException e) {
        return new ErrorForm("400", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotExistConditionException.class)
    public ErrorForm notExistConditionExHandler(NotExistConditionException e) {
        return new ErrorForm("400", e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundResultException.class)
    public ErrorForm notFoundResultExHandler(NotFoundResultException e) {
        return new ErrorForm("404", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExistMemberException.class)
    public ErrorForm existMemberExHandler(ExistMemberException e) {
        return new ErrorForm("400", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotScannedException.class)
    public ErrorForm notScannedExHandler(NotScannedException e) {
        return new ErrorForm("400", e.getMessage());
    }
}