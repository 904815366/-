package com.example.homeservice.web.controller;

import com.example.homeservice.dao.mysql.LoginLogDao;
import com.example.homeservice.dao.mysql.LoginLogDtoDao;
import com.example.homeservice.utils.ResponseResult;
import com.example.homeservice.web.dto.LoginlogDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class LoginLogController {

    @Resource
    private LoginLogDao loginLogDao;
    @Resource
    private LoginLogDtoDao loginLogDtoDao;

    @GetMapping("/loginLog")
    public ResponseResult<Page> findLogByUsername(@RequestParam(name = "username", defaultValue = "") String username,
                                                  @RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
                                                  @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize){


        Sort sort = Sort.by(Sort.Direction.DESC, "logintime");
        Pageable Pageable = PageRequest.of(pageNum, pageSize, sort);
        Page<LoginlogDto> page = loginLogDtoDao.findAllByUsernameLike(username, Pageable);
        return new ResponseResult<>(200,"success",page);
    }

}
