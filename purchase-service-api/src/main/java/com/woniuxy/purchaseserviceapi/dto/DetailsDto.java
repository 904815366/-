package com.woniuxy.purchaseserviceapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailsDto implements Serializable {
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime invoiceTime;
    private String invoiceNumber;
    private String businessType;
    private String supplierName;
    private Long goodsId;
    private String goodsName;
    private String size;
    private String unit;
}
