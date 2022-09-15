package com.example.homeservice.dao.mysql;

import com.example.homeservice.dao.po.LoginlogPo;
import com.example.homeservice.web.dto.LoginlogDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoginLogDtoDao extends JpaRepository<LoginlogDto,Long> {
    Page<LoginlogDto> findAllByUsernameLike(String username, Pageable pageable);

}
