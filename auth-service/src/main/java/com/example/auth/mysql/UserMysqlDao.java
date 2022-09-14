package com.example.auth.mysql;

import com.example.auth.mysql.po.UserPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMysqlDao extends JpaRepository<UserPo,Long> {

    Optional<UserPo> findByUsername(String username);
}
