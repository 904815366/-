package com.example.homeservice.web.dto;

import com.example.homeservice.dao.po.RolePo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

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
    private Date createtime;
    private String avatar;
    private Integer version;


}
