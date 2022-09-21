package com.example.homeservice.dao.po;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customers", schema = "erp")
public class CustomersPo {
    @Id
    private Long id;
    private String name;
    private String linkman;
    private Integer tel;
    private Timestamp registrationDate;
    private Timestamp lastLoginTime;
    private Long levelId;
    private String status;



}
