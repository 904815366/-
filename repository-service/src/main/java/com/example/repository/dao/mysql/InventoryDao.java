package com.example.repository.dao.mysql;

import com.example.repository.entity.po.InventoryPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InventoryDao extends JpaRepository<InventoryPo,Long> {

}
