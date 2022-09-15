package com.example.homeservice.dao.po;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "loginlog", schema = "erp")
public class LoginlogPo {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime logintime;
    private String ip;
    private String adder;
    private String systems;
    private String browser;
    private String username;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginlogPo that = (LoginlogPo) o;
        return id == that.id && Objects.equals(logintime, that.logintime) && Objects.equals(ip, that.ip) && Objects.equals(adder, that.adder) && Objects.equals(systems, that.systems) && Objects.equals(browser, that.browser)&& Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, logintime, ip, adder, systems, browser, username);
    }
}
