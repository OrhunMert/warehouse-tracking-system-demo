package com.trackingsystem.warehouse.exception;

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
public class WarehouseExceptionController extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        WarehouseNotValidException warehouseNotValidException = new WarehouseNotValidException(HttpStatus.BAD_REQUEST,
                "Validation Error",
                ex.getBindingResult().toString());

        log.info("Validation Error Result:{}",warehouseNotValidException.getDetails());

        return new ResponseEntity<>(warehouseNotValidException.getDetails(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {WarehouseNotFoundException.class})
    public ResponseEntity<String> warehouseNoutFound(WarehouseNotFoundException warehouseNotFoundException){
        return new ResponseEntity<>(warehouseNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {UserNotFoundforWarehouseException.class})
    public ResponseEntity<String> userNotFoundForWarehouse(UserNotFoundforWarehouseException userNotFoundforWarehouseException){
        return new ResponseEntity<>(userNotFoundforWarehouseException.getMessage(),HttpStatus.NOT_FOUND);
    }

}
