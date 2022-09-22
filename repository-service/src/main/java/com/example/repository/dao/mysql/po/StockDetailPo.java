package com.example.repository.dao.mysql.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Builder
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock_detail", schema = "erp")
public class StockDetailPo {
    @Id
    @Column(nullable = false)
    private String id;
    private Integer type;
    private Long goodsid;
    private Integer num;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;
    private Integer status;
}
