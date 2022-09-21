package com.example.homeservice.dao.mysql;


import com.example.homeservice.dao.po.CustomersPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomersDao extends JpaRepository<CustomersPo,Long> {


}
