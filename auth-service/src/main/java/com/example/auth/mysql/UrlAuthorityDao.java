package com.example.auth.mysql;

import com.example.auth.mysql.po.UrlAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlAuthorityDao extends JpaRepository<UrlAuthority,Long> {

}
