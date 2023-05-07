package com.example.warehouse.repository;

import com.example.warehouse.domain.PlannedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlannedItemRopository extends JpaRepository<PlannedItem , Integer> {
    List<PlannedItem> findByplannednumber(int plannedsnumber);
}
