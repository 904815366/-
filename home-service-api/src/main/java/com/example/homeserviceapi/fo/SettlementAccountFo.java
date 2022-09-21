package com.example.homeserviceapi.fo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettlementAccountFo {
    private Long id;
    private String account;
    private Double balance;
    private Integer openingBalance;
    private Long accountTypeId;
    private LocalDateTime creationTime;
    private String status;
    private Integer version;
}
