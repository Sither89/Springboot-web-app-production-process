package com.example.warehouse.repository;

import com.example.warehouse.domain.SaleOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleOrderRepository extends JpaRepository <SaleOrder , Integer> {
    List<SaleOrder> findBysalesnumber(int salesnumber);
}
