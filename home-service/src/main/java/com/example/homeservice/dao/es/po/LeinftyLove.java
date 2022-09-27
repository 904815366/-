package com.example.homeservice.dao.es.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "leinfty-love*", shards = 1, replicas = 0)
public class LeinftyLove {

    @Id
    @Field(name="_id", type = FieldType.Keyword)
    private String id;

    @Field(name="@timestamp", type = FieldType.Date,format = DateFormat.custom, pattern = "yyyy-MM-dd")
    private String timestamp;
    //             "logger_name" : "com.example.homeservice.service.SignService",
    @Field(name="logger_name", type = FieldType.Text)
    private String loggerName;

    @Field(name="message", type = FieldType.Text)
    private String message;
}
