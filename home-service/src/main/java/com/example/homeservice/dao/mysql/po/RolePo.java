package com.example.homeservice.dao.mysql.po;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "role", schema = "erp")
public class RolePo {
    @Id
    private long id;
    private String name;


}
