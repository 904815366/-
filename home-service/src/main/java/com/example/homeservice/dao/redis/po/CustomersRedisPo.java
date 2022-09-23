package com.example.homeservice.dao.redis.po;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("customers")
public class CustomersRedisPo {

    @Id
    private Long id;
    private String name;
    private String linkman;
    private Integer tel;
    private LocalDateTime registrationDate;
    private LocalDateTime lastLoginTime;
    private Long levelId;
    private String status;



}
