package com.example.homeservice.dao.po;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "settlement_account", schema = "erp")
public class SettlementAccountPo {
    @Id
    private Long id;
    private String account;
    private Integer balance;
    private Integer openingBalance;
    private Long accountTypeId;
    private Timestamp creationTime;
    private String status;
    private Integer version;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SettlementAccountPo that = (SettlementAccountPo) o;
        return id == that.id && Objects.equals(account, that.account) && Objects.equals(balance, that.balance) && Objects.equals(openingBalance, that.openingBalance) && Objects.equals(accountTypeId, that.accountTypeId) && Objects.equals(creationTime, that.creationTime) && Objects.equals(status, that.status) && Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, balance, openingBalance, accountTypeId, creationTime, status, version);
    }
}
