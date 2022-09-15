package com.example.homeservice.web.controller;

import com.example.homeservice.dao.mysql.LoginLogDao;
import com.example.homeservice.dao.mysql.LoginLogDtoDao;
import com.example.homeservice.dao.po.SignPo;
import com.example.homeservice.repository.SignRepository;
import com.example.homeservice.service.SignService;
import com.example.homeservice.utils.IpUtil;
import com.example.homeservice.utils.ResponseResult;
import com.example.homeservice.utils.SimpleFormatUtil;
import com.example.homeservice.web.converter.SignConverter;
import com.example.homeservice.web.dto.LoginlogDto;
import com.example.homeservice.web.dto.PageDto;
import com.example.homeservice.web.dto.SignsCountDto;
import com.example.homeservice.web.dto.SignsDto;
import com.example.homeservice.web.fo.QuerySignListFo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class SignController {
    @Resource
    private SignService signService;
    @Resource
    private SignRepository signRepository;
    @Resource
    private SignConverter signConverter;

    /**
     * 根据 username  status todaytime 动态查询签到列表
     *
     * @param querySignListFo username  status todaytime
     * @return sign集合
     */
    @GetMapping("/sign")
    public ResponseResult<PageDto> querySigns(QuerySignListFo querySignListFo) {
        return querySignListFo.exec();
    }


    /**
     * 员工签到
     *
     * @param username 用户名
     * @param status   状态 [1] 已签到
     * @param request
     */
    @PutMapping("/sign")
    public ResponseResult<Void> modifyStatus(String username, String status, HttpServletRequest request) {
        try {
            //获取当前时间 yyyy-MM-dd
            String todaytime = SimpleFormatUtil.dateForString(System.currentTimeMillis());
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String todaytime = simpleDateFormat.format(System.currentTimeMillis());
            String yyyy_MM_dd = todaytime.substring(0, 10);

            SignPo po = SignPo.builder().username(username).status(status).todaytime(yyyy_MM_dd).build();
            SignPo signPo = signRepository.findByUsername(po).orElseThrow(() -> new NullPointerException("根据用户名,未查询到职员"));
            log.info("查询到当前用户未签到,开始签到业务逻辑");
            String ip = IpUtil.getIpAddress(request);
            signPo.setStatus("1");
            signPo.setSignip(IpUtil.getIpAddress(request));
            signPo.setSigntime(LocalDateTime.now());
            signService.modifyStatus(signPo);
            log.info("modifyStatus方法,签到成功");
            return new ResponseResult<>(200, "签到成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(400, "今日已经签到过了,无需再签到", null);
        }
    }


    /**
     * 根据日期查询当日员工总数  签到数  未签到数
     * @param todaytime 年-月-日
     * @return
     */
    @GetMapping("/sign/count")
    public ResponseResult<SignsCountDto> findCount(String todaytime) {
        //查询
        List<SignPo> pos = signRepository.findAllByTodaytime(todaytime);
        //筛选 已签到 和 未签到
        Long signFalse = pos.stream().filter(signPo -> signPo.getStatus().equals("0")).count();
        Long signTrue = pos.stream().filter(signPo -> signPo.getStatus().equals("1") ).count();

        log.info("{}-{}",signTrue,signFalse);

        SignsCountDto signsCountDto = SignsCountDto.builder().userCount(pos.size()).signTrue(signTrue).signFalse(signFalse).build();
        return new ResponseResult<>(200,"success",signsCountDto);

    }

}
