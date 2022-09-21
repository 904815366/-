package com.example.homeservice.web.fo;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;


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
