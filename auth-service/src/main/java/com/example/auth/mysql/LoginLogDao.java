package com.example.auth.mysql;

import com.example.auth.mysql.po.LoginlogPo;
import com.example.auth.mysql.po.UsersPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginLogDao extends JpaRepository<LoginlogPo,Long> {


}
