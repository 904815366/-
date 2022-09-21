package com.example.homeservice.dao.mysql;

import com.example.homeservice.dao.mysql.po.SignPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SignDao extends JpaRepository<SignPo,Long> {

    List<SignPo> findAllByTodaytimeEquals(String todaytime);

//    Optional<SignPo> findByUsernameAndStatusEqualsAndTodaytime(SignPo signPo);

}
