package com.example.repository.entity.po;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "goods", schema = "erp")
public class GoodsPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "goods_type_id")
    private GoodsTypePo goodsType;
    private String size;
    private Integer stock;

}
