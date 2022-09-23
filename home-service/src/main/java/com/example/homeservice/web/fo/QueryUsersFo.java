package com.example.homeservice.web.fo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;


@Slf4j
@Data
public class QueryUsersFo {


    private String username;
    private String status;
    private LocalDateTime createtime;
    private Integer num;
    private Integer size;

}
