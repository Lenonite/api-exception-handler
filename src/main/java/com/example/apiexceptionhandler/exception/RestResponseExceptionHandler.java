package com.example.apiexceptionhandler.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private ApiError message(int status,Exception ex){
        String message=ex==null ? "Unknown Error": ex.getMessage();
        String developerMessage= ExceptionUtils.getRootCauseMessage(ex);
        return new ApiError(status,message,developerMessage);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleExceptionInternal(ex,message(status.value(),ex),headers,status,request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
      return handleExceptionInternal(ex,message(status.value(),ex),headers,status,request);
    }
    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public  ResponseEntity<Object> handleException(RuntimeException ex,WebRequest request){
        return handleExceptionInternal(ex,message(HttpStatus.BAD_REQUEST.value(),ex),new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
    }
}
