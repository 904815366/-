package com.example.homeservice.dao.redis.po;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.sql.Timestamp;
<<<<<<< master
=======
import java.time.LocalDateTime;
>>>>>>> [09/21 16:48 罗虎]  添加了modifySettlement
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("settlement_account")
public class SettlementAccountRedisPo {
    @org.springframework.data.annotation.Id
    private Long id;
    private String account;
<<<<<<< master
    private Integer balance;
    private Integer openingBalance;
    private Long accountTypeId;
    private Timestamp creationTime;
=======
    private Double balance;
    private Integer openingBalance;
    private Long accountTypeId;
    private LocalDateTime creationTime;
>>>>>>> [09/21 16:48 罗虎]  添加了modifySettlement
    private String status;
    private Integer version;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SettlementAccountRedisPo that = (SettlementAccountRedisPo) o;
        return id == that.id && Objects.equals(account, that.account) && Objects.equals(balance, that.balance) && Objects.equals(openingBalance, that.openingBalance) && Objects.equals(accountTypeId, that.accountTypeId) && Objects.equals(creationTime, that.creationTime) && Objects.equals(status, that.status) && Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, balance, openingBalance, accountTypeId, creationTime, status, version);
    }
}
