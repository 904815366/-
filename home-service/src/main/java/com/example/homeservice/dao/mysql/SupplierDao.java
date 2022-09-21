package com.example.homeservice.dao.mysql;


import com.example.homeservice.dao.po.SupplierPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SupplierDao extends JpaRepository<SupplierPo,Long> {


}
