package com.woniu.web.fo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.woniu.dao.po.CusOrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCusAndDetailFo {
    private List<CusOrderDetail> cusOrderDetails;

    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ordertime;

    private String sitename;

    private String phone;

    private String site;

    private Double money;

    private String busibess;

    private Long cusId;

    private String status;

    private Long uId;

    private String prepar;

    private String auditor;

    private String version;

//    private String name;
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
private Date deliverytime;

}
