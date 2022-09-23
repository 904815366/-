package com.example.homeservice.dao.mysql;

import com.example.homeservice.dao.mysql.po.LoginlogPo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoginLogDao extends JpaRepository<LoginlogPo,Long> {
    Page<LoginlogPo> findAllByUsernameLike(String username, Pageable pageable);

}
