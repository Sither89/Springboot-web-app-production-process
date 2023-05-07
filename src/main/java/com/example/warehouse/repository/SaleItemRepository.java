package com.example.warehouse.repository;

import com.example.warehouse.domain.SaleItem;
import com.example.warehouse.domain.SaleOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, Integer> {
    List<SaleItem> findBysalenumber(int salesnumber);
}
