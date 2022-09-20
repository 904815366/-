package com.example.repository.dao.mysql;

import com.example.repository.entity.po.GoodsPo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsDao extends JpaRepository<GoodsPo,Long> {
}
