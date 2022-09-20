package com.example.repository.web.controller.advice;

import com.example.repository.exception.NotEnoughStockException;
import com.example.repository.util.ResponseResult;
import com.example.repository.web.controller.GoodsController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = GoodsController.class)
public class GoodsControllerHandler {
    @ExceptionHandler(NotEnoughStockException.class)
    public ResponseResult<Void> handleNotEoughStock(NotEnoughStockException e){
        return new ResponseResult<>(404,"库存不足",null);
    }
}
