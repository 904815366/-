package com.woniuxy.purchase.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("message")
public class MessagePo {
    private Long id;
    private String exchange;
    private String routingKey;
    private String content;
    private Integer retryCount;
    private String status;
}
