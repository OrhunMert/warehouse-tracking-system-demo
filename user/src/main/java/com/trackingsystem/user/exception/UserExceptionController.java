package com.trackingsystem.user.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class UserExceptionController extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        UserNotValidException userNotValidException = new UserNotValidException(HttpStatus.BAD_REQUEST,
                "Validation Error",
                ex.getBindingResult().toString());

        log.info("Validation Error Result:{}",ex.getBindingResult().toString());

        return new ResponseEntity<>(userNotValidException.getDetails(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<String> userNotFound(UserNotFoundException userNotFoundException){
        return new ResponseEntity<>(userNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

}
