package com.example.homeservice.dao.redis.po;

import com.example.homeservice.dao.mysql.po.RolePo;
import lombok.*;
import org.hibernate.Hibernate;
<<<<<<< master
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
=======
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
>>>>>>> [09/21 16:48 罗虎]  添加了modifySettlement
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("users")
<<<<<<< master
public class UsersRedisPo implements Serializable {
    @org.springframework.data.annotation.Id
=======
public class UsersRedisPo{
    @Id
>>>>>>> [09/21 16:48 罗虎]  添加了modifySettlement
    private Long id;
    private String username;
    private String password;
    private String name;
    private String tel;
    private String email;
    private RolePo role;
    private String status;
<<<<<<< master
    private Date createtime;
=======
    private LocalDateTime createtime;
>>>>>>> [09/21 16:48 罗虎]  添加了modifySettlement
    private Integer version;
    private String avatar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsersRedisPo usersPo = (UsersRedisPo) o;
        return id != null && Objects.equals(id, usersPo.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
