package com.example.repository.dao.mysql;

import com.example.repository.dao.mysql.po.InventoryPo;
import com.example.repository.dao.mysql.po.StockDetailPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StockDetailDao extends JpaRepository<StockDetailPo,String> {
  //  public List<StockDetailPo> findAllByGoods_IdAndTimeBeforeAndTimeAfterAndStatus();
}
