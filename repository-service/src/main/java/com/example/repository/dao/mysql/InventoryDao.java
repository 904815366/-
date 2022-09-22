package com.example.repository.dao.mysql;

import com.example.repository.dao.mysql.po.InventoryPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface InventoryDao extends JpaRepository<InventoryPo,Long> {
}
