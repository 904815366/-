package com.woniuxy.purchase.web.exception;

import com.example.util.ResponseResult;
import com.woniuxy.purchase.web.controller.PurchaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = PurchaseController.class)
@Slf4j
public class PurchaseControllerException {
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult<String> exc(RuntimeException e){
        e.printStackTrace();
        if (e instanceof NullPointerException){
            return new ResponseResult<>(500,"ERRO","空指针异常");
        }
        return new ResponseResult<>(500,"ERRO","系统异常");
    }
}
