package com.example.homeservice.web.fo;


import com.example.homeservice.dao.po.RolePo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;


@Slf4j
@Data
public class AddUsersFo {

    private Long id;
    private String username;
    private String password;
    private String name;
    private String tel;
    private String email;
    private Integer roleid;
    private String status;
    private String avatar;
    private Integer version;

}
