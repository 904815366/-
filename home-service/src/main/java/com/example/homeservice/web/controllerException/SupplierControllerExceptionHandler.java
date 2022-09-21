package com.example.homeservice.web.controllerException;


import com.example.homeservice.utils.ResponseResult;
import com.example.homeservice.web.controller.SupplierController;
import com.example.homeservice.web.controller.UsersController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice(assignableTypes = SupplierController.class)
public class SupplierControllerExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public ResponseResult<String> xxx(RuntimeException e) {
        if (e instanceof NullPointerException) {
            return new ResponseResult<>(403,e.getMessage(),"N/A");
        } else if (e instanceof IndexOutOfBoundsException) {
            return new ResponseResult<>(403,e.getMessage(),"N/A");
        } else if (e instanceof NoSuchElementException) {
            return new ResponseResult<>(403,e.getMessage(),"N/A");
        }else {
            return new ResponseResult<>(403,e.getMessage(),"N/A");
        }
    }

}
