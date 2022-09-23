package com.example.homeservice.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignsDto {

    private Long id;
    private String username;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime signtime;
    private String signip;
    private String todaytime;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignsDto signPo = (SignsDto) o;
        return id == signPo.id && Objects.equals(username, signPo.username) && Objects.equals(status, signPo.status) && Objects.equals(signtime, signPo.signtime) && Objects.equals(signip, signPo.signip) && Objects.equals(todaytime, signPo.todaytime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, status, signtime, signip, todaytime);
    }
}
