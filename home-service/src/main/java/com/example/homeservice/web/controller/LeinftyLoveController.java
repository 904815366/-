package com.example.homeservice.web.controller;

import com.example.homeservice.dao.es.LeinftyLoveRepository;
import com.example.homeservice.dao.es.po.LeinftyLove;
import com.example.homeservice.utils.ResponseResult;
import com.example.homeservice.web.converter.LeinftyLoveConverter;
import com.example.homeservice.web.dto.LeinftyLoveDto;
import com.example.homeservice.web.fo.LeinftyLoveFo;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class LeinftyLoveController {
    @Resource
    private LeinftyLoveRepository leinftyLoveRepository;
    @Resource
    private LeinftyLoveConverter leinftyLoveConverter;


    @GetMapping("/leinftyLove")
    public ResponseResult<Page<LeinftyLove>> findList(LeinftyLoveFo fo){
        log.info("进入findList方法");
        fo.verify();
        System.out.println(fo);
        Pageable pageable = PageRequest.of(fo.getNum(), fo.getSize());
        Page<LeinftyLove> page = leinftyLoveRepository.findList(fo.getMessage(), fo.getTimestamp(), fo.getLoggerName(), pageable);
        page.map(leinftyLove -> leinftyLoveConverter.from(leinftyLove));
        return new ResponseResult<>(200,"success",page);
    }
}
