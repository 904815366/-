package com.example.homeservice.web.fo;

import com.example.homeservice.dao.mysql.SignDao;
import com.example.homeservice.dao.mysql.UsersDao;
import com.example.homeservice.dao.po.SignPo;
import com.example.homeservice.dao.po.UsersPo;
import com.example.homeservice.repository.SignRepository;
import com.example.homeservice.service.UsersService;
import com.example.homeservice.utils.ApplicationContextHolder;
import com.example.homeservice.utils.ResponseResult;
import com.example.homeservice.web.converter.SignConverter;
import com.example.homeservice.web.dto.PageDto;
import com.example.homeservice.web.dto.SignsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.*;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Data
public class QuerySignListFo {

    private String username;
    private String status;
    private String todaytime;
    private Integer num = 0;
    private Integer size = 5;


    public ResponseResult<PageDto> exec() {
        log.info("进入querySignListFo的exec方法");
        username = "".equals(username) ? null : username;
        status = "".equals(status) ? null : status;
        todaytime = "".equals(todaytime) ? null : todaytime;

        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        SignRepository signRepository = applicationContext.getBean(SignRepository.class);

        Pageable pageable = PageRequest.of(num, size);
        SignPo signPo = SignPo.builder().username(username).status(status).todaytime(todaytime).build();
        Example<SignPo> example = Example.of(signPo);

        PageDto<SignsDto> dtoPage = signRepository.findAll(example, pageable);

        return new ResponseResult<>(200, "success", dtoPage);
    }
}
