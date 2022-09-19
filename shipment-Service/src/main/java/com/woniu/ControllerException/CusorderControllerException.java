package com.woniu.ControllerException;

import com.woniu.web.CusorderController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = CusorderController.class)
public class CusorderControllerException {

    @ExceptionHandler(RuntimeException.class)
    public String exc(RuntimeException e){
        if (e instanceof NullPointerException){
            return "空指针异常："+e.getMessage();
        }
        return "错误操作请重新操作";
    }

}
