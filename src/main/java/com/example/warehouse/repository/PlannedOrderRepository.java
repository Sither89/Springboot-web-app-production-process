package com.example.warehouse.repository;

import com.example.warehouse.domain.PlannedOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlannedOrderRepository extends JpaRepository<PlannedOrder , Integer>{
    List<PlannedOrder> findByplannedsnumber(int plannedsnumber);
}
