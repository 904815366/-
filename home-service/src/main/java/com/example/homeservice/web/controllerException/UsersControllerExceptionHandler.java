package com.example.homeservice.web.controllerException;


import com.example.homeservice.utils.ResponseResult;
import com.example.homeservice.web.controller.UsersController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice(assignableTypes = UsersController.class)
public class UsersControllerExceptionHandler {

    /**
     *  RegisterController 中的方法但凡抛出了 RuntimeException 异常，都会"导致"下面这个方法的执行。
     *  Controller 中"抛"的异常会作为这个方法的参数传到方法里面来。
     */
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
