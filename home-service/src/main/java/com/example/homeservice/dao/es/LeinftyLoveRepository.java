package com.example.homeservice.dao.es;

import com.example.homeservice.dao.es.po.LeinftyLove;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeinftyLoveRepository extends ElasticsearchRepository<LeinftyLove, String> {

    Page<LeinftyLove> findAllByLoggerNameLikeAndTimestampAndMessageLike(Pageable pageable);

    //    GET /leinfty-love*/_search
//    {
//        "query" : {
//        "bool" : {
//            "must": [
//            { "query_string" : { "query" : "*sign*", "fields" : [ "logger_name" ] } },
//            { "query_string" : { "query" : "当前不是", "fields" : [ "message" ] } } ,
//            { "query_string" : { "query" : "2022-09-24", "fields" : [ "@timestamp" ] } }
//			]
//        }
//    }
//    }
    @Query("{\"bool\" : { \n" +
            "\t\t\t\"should\": [ \n" +
            "\t\t\t{\"match\": {\"message\": \"?0\"}},\n" +
            "\t\t\t{\"match\": {\"@timestamp\": \"?1\"}},\n" +
            "\t\t\t{\"wildcard\": {\"logger_name\": \"*?2*\"}}\n" +
            "\t\t\t] \n" +
            "    }}")
    Page<LeinftyLove> findList(String message, String timestamp, String loggerName, Pageable pageable);
}
