package com.example.repository.web.controller.advice;

import com.example.repository.exception.NotEnoughStockException;
import com.example.repository.util.ResponseResult;
import com.example.repository.web.controller.InventoryController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice(assignableTypes = InventoryController.class)
public class InventoryControllerHandler {
        @ExceptionHandler(NotEnoughStockException.class)
        public ResponseResult<Void> handleNotEoughStock(NotEnoughStockException e){
            return new ResponseResult<>(404,"库存不足",null);
        }
}
