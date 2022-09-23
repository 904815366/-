package com.example.homeservice.dao.redis.po;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("supplier")
public class SupplierRedisPo {
    @org.springframework.data.annotation.Id
    private Long id;
    private String name;
    private String linkman;
    private Integer tel;
    private String email;
    private Integer initialBalance;
    private String status;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplierRedisPo that = (SupplierRedisPo) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(linkman, that.linkman) && Objects.equals(tel, that.tel) && Objects.equals(email, that.email) && Objects.equals(initialBalance, that.initialBalance) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, linkman, tel, email, initialBalance, status);
    }
}
