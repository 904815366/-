package com.example.repository.dao.mysql.po;

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
@Table(name = "inventory", schema = "stock_detail")
public class StockDetailPo {
    @Id
    @Column(nullable = false)
    private Long id;
    private Integer type;
    private Long goodsid;
    private Integer num;
    private LocalDateTime time;
    private String datetime;
    private String status;
}
