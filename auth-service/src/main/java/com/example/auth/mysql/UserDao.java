package com.example.auth.mysql;

import com.example.auth.mysql.po.UsersPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UsersPo,Long> {

    Optional<UsersPo> findByUsername (String username);
}
