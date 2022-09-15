package com.example.homeservice.dao.mysql;

import com.example.homeservice.dao.po.SignPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SignDao extends JpaRepository<SignPo,Long> {

    List<SignPo> findAllByTodaytimeEquals(String todaytime);

//    Optional<SignPo> findByUsernameAndStatusEqualsAndTodaytime(SignPo signPo);

}
