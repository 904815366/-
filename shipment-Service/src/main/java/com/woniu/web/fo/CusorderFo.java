package com.woniu.web.fo;

import lombok.Data;

@Data
public class CusorderFo {
    private Long cid;
    private String stareTime;
    private String endTime;
    private Long uId;
    private String statu;
    private String busibess;

    private Integer pageSize;
    private Integer pageNum;
}
