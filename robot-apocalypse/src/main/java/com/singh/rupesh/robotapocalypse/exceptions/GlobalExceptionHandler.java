package com.singh.rupesh.robotapocalypse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ErrorResponse handleNotFoundExceptions(Exception ex) {

        return createErrorResponse("404", ex);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(UnProcessableException.class)
    @ResponseBody
    public ErrorResponse handleInternalServerException(Exception ex) {
        return createErrorResponse("422", ex);
    }

    private ErrorResponse createErrorResponse(String code, Exception ex) {
        final String message = ex.getMessage();
        return new ErrorResponse(code, message);
    }

}
