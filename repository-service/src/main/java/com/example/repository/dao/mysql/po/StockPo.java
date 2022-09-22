package com.example.repository.dao.mysql.po;

import lombok.*;

import javax.persistence.*;
@Builder
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inventory", schema = "stock")
public class StockPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private Long goodsid;   //商品id
    private Integer innum;  //入库数量
    private Integer outnum; //出库数量
    private Integer status; //状态
}
