package com.example.homeservice.dao.mysql;


import com.example.homeservice.dao.mysql.po.SettlementAccountPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SettlementAccountDao extends JpaRepository<SettlementAccountPo,Long> {


}
