package com.example.repository.entity.po;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inventory", schema = "erp")
public class InventoryPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
