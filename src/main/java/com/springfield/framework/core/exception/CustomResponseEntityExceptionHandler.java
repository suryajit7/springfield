package com.springfield.framework.core.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

/*    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleException(Exception exception, WebRequest request){
        return new ResponseEntity(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

}
