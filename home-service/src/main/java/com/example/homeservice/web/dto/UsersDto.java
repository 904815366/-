package com.example.homeservice.web.dto;

import com.example.homeservice.dao.mysql.po.RolePo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

<<<<<<< master
=======
import java.time.LocalDateTime;
>>>>>>> [09/21 16:48 罗虎]  添加了modifySettlement
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {

    private Long id;
    private String username;
    private String password;
    private String name;
    private String tel;
    private String email;
    private RolePo role;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createtime;
    private String avatar;
    private Integer version;


}
