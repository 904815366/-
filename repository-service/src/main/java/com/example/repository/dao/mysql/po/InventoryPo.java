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
@Table(name = "inventory", schema = "erp")
public class InventoryPo {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "goodsid")
    private GoodsPo goods;
    private Integer stocktake;
    private Integer profitloss;
    private Integer status;
    private String remark;
}
