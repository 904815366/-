package com.example.homeservice.dao.mysql;

import com.example.homeservice.dao.mysql.po.UsersPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersDao extends JpaRepository<UsersPo,Long> {

    Optional<UsersPo> findByUsernameAndPassword(String username, String password);

    List<UsersPo> findAllByUsernameNotAndStatusEquals(String username, String status);

    Optional<UsersPo> findByUsername(String username);

    Optional<UsersPo> findByIdAndStatusNot(Long id, String status);

    @Modifying
    @Query(value = "update users set username=?1,password=?2,name=?3,tel=?4,email=?5,role_id=?6,status=?7,version=version+1,avatar=?8 where id=?9 and version=?10",nativeQuery = true)
    int modifyUsers(String username, String password, String name,
                    String tel, String email, Integer roleid, String status,
                    String avatar, Long id, Integer version);
}
