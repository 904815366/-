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
@Table(name = "stock", schema = "erp")
public class StockPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "goodsid")
    private GoodsPo goods;
    private Integer innum;  //入库数量
    private Integer outnum; //出库数量
    private Integer status; //状态
}
