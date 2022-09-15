package com.example.homeservice.service;

import com.example.homeservice.dao.po.SignPo;
import com.example.homeservice.dao.po.UsersPo;
import com.example.homeservice.repository.SignRepository;
import com.example.homeservice.repository.UsersRepository;
import com.example.homeservice.utils.SnowflakeIdGenerator;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
public class SignService {
    private final String USERNAME_ADMIN = "admin";
    private final String ADD_SIGN_TIME = "08";
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private UsersRepository usersRepository;
    @Resource
    private SignRepository signRepository;


    /**
     * 签到业务逻辑
     * 员工: 点击签到 -> 通过username和时间查询状态是否为 [已签到] -> 是则 return 今日已签到.
     * 否则将状态改为 [已签到], return 签到成功
     * 可通过ID和时间进行分页条件查询
     * <p>
     * 管理员: 设置定时任务 , 每天早上9:00查询所有在职员工(users表) ->
     * 查询根据日期查询sign表, 如果不为空,则不执行业务逻辑
     * 如果为空,创建sign集合,设置username, 状态设置为 [未签到], 设置创建时间, saveAll到sign表.
     * 可查询所有
     * 可通过username和时间和状态进行分页条件查询
     */
    @RabbitListener(queues = "add_sign")
    public void addSigns() {
        log.info("进入了addSigns方法");

        //雪花ID
        SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(1, 1);

        //获取当前时间 yyyy-MM-dd
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String todaytime = simpleDateFormat.format(System.currentTimeMillis());
        String yyyy_MM_dd = todaytime.substring(0, 10);
        String HH_mm = todaytime.substring(11, 13);

        if (!HH_mm.equals(ADD_SIGN_TIME)) {
            log.info("当前不是:{}点,停止业务逻辑的执行", ADD_SIGN_TIME);
            rabbitTemplate.convertAndSend("add_sign_delayed", "add_sign_delayed");
            return;
        }

        log.info("查询sign表是否有日期:{}的数据", yyyy_MM_dd);
        List<SignPo> signPos = signRepository.findAllByTodaytime(yyyy_MM_dd);
        boolean signPos_empty = CollectionUtils.isEmpty(signPos);
        if (signPos_empty) {
            log.info("日期:{}没有今日的签到表,查询所有员工", yyyy_MM_dd);
            List<UsersPo> usersPos = usersRepository.findUsersByUsernameAndStatus(USERNAME_ADMIN, "0");
            log.info("创建sign集合,遍历添加数据");
            List<SignPo> signPoList = new ArrayList<>();
            for (UsersPo usersPo : usersPos) {
                signPoList.add(SignPo.builder()
                        .id(snowflakeIdGenerator.nextId())
                        .username(usersPo.getUsername())
                        .todaytime(todaytime).status("0").build());
            }
            signRepository.addSigns(signPoList);
            log.info("日期:{},员工签到表添加成功", yyyy_MM_dd);
        }

        rabbitTemplate.convertAndSend("add_sign_delayed", "add_sign_delayed");
    }

    public void modifyStatus(SignPo signPo) {
        signRepository.modifyStatus(signPo);
    }
}
