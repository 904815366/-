package com.example.homeservice.web.fo;

import com.example.homeservice.utils.SimpleFormatUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeinftyLoveFo {
    private String message;
    private String timestamp;
    private String loggerName;
    private Integer num;
    private Integer size;

    public void verify() {
        message = message == null ? "" : message;
        timestamp = timestamp == null||"".equals(timestamp) ? SimpleFormatUtil.dateForString(System.currentTimeMillis()) : timestamp;
        loggerName = loggerName == null ? "" : loggerName.toLowerCase();
        num = num == null ? 0 : num;
        size = size == null ? 5 : size;
    }
}
