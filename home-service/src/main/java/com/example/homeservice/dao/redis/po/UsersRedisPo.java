package com.example.homeservice.dao.redis.po;

import com.example.homeservice.dao.mysql.po.RolePo;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("users")
public class UsersRedisPo{
    @Id
    private Long id;
    private String username;
    private String password;
    private String name;
    private String tel;
    private String email;
    private RolePo role;
    private String status;
    private LocalDateTime createtime;
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
