package com.example.repository.repository;

import com.example.homeservice.web.dto.PageDto;
import com.example.repository.dao.mysql.po.StockPo;
import com.example.repository.web.controller.dto.StockDto;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;

public class StockRepository {
    public PageDto<StockDto> findAll(Example<StockPo> example, Pageable pageable) {
    }
}
