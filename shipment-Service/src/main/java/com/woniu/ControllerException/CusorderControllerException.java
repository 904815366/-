package com.woniu.ControllerException;

import com.example.util.ResponseResult;
import com.woniu.web.CusorderController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = CusorderController.class)
public class CusorderControllerException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseResult<String> exc(RuntimeException e){
        if (e instanceof NullPointerException){
            return new ResponseResult<>(200,"ERRO","空指针异常");
        }
        return new ResponseResult<>(200,"ERRO","错误");
    }

}
