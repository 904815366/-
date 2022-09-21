package com.example.homeservice.dao.mysql.po;

import lombok.*;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customers", schema = "erp")
public class CustomersPo  {
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
