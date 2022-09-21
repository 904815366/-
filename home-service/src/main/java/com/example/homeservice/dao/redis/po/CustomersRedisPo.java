package com.example.homeservice.dao.redis.po;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import java.sql.Timestamp;
<<<<<<< master
=======
import java.time.LocalDateTime;
>>>>>>> [09/21 16:48 罗虎]  添加了modifySettlement

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("customers")
<<<<<<< master
public class CustomersRedisPo  {
=======
public class CustomersRedisPo {
>>>>>>> [09/21 16:48 罗虎]  添加了modifySettlement
    @Id
    private Long id;
    private String name;
    private String linkman;
    private Integer tel;
<<<<<<< master
    private Timestamp registrationDate;
    private Timestamp lastLoginTime;
=======
    private LocalDateTime registrationDate;
    private LocalDateTime lastLoginTime;
>>>>>>> [09/21 16:48 罗虎]  添加了modifySettlement
    private Long levelId;
    private String status;



}
