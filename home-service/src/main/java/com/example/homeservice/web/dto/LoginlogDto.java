package com.example.homeservice.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
public class LoginlogDto {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
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
        LoginlogDto that = (LoginlogDto) o;
        return id == that.id && Objects.equals(logintime, that.logintime) && Objects.equals(ip, that.ip) && Objects.equals(adder, that.adder) && Objects.equals(systems, that.systems) && Objects.equals(browser, that.browser)&& Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, logintime, ip, adder, systems, browser, username);
    }
}
