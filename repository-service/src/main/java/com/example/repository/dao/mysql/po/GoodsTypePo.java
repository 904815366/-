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

@Table(name = "goods_type", schema = "erp")
public class GoodsTypePo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String name;
}
