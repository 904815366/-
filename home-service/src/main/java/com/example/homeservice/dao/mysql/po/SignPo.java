package com.example.homeservice.dao.mysql.po;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "sign", schema = "erp")
public class SignPo {
    @Id
    private Long id;
    private String username;
    private String status;
    private LocalDateTime signtime;
    private String signip;
    private String todaytime;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignPo signPo = (SignPo) o;
        return id == signPo.id && Objects.equals(username, signPo.username) && Objects.equals(status, signPo.status) && Objects.equals(signtime, signPo.signtime) && Objects.equals(signip, signPo.signip) && Objects.equals(todaytime, signPo.todaytime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, status, signtime, signip, todaytime);
    }
}
