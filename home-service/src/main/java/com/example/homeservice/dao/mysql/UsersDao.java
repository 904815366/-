package com.example.homeservice.dao.mysql;

import com.example.homeservice.dao.mysql.po.UsersPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersDao extends JpaRepository<UsersPo,Long> {

    Optional<UsersPo> findByUsernameAndPassword(String username, String password);

    List<UsersPo> findAllByUsernameNotAndStatusEquals(String username, String status);

    Optional<UsersPo> findByUsername(String username);

    Optional<UsersPo> findByIdAndStatusNot(Long id, String status);


}
