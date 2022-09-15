package com.example.auth.mysql;

import com.example.auth.mysql.po.PermsPo;
import com.example.auth.mysql.po.UsersPo;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PermsDao extends JpaRepository<PermsPo,Long> {

    @Query(value = "select p.authority from role_perm as rp, perms as p where rp.perm_id=p.id and  rp.role_id = ?1", nativeQuery = true)
    Set<String> findUriAllByRoleId(Long roleId);

}
