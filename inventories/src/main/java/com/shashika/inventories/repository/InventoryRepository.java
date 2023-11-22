package com.shashika.inventories.repository;

import com.shashika.inventories.entity.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {


    Page<Inventory> findAll(Pageable pageable);
    @Query("SELECT i FROM Inventory i " +
            "WHERE " +
            " (:brand is null or i.brand in :brand) " +
            "and (:type is null or i.type in :type) " +
            "and (:description is null or lower(i.description) like %:description%)")
    Page<Inventory> search(
            @Param("brand") List<String> brand,
            @Param("type") List<String> type,
            @Param("description") String description,
            Pageable pageable
    );



}
