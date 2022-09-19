package com.woniuxy.purchase.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagePo {
    private Long id;
    private String exchange;
    private String routingKey;
    private String content;
    private Integer retryCount;
    private String status;
}
