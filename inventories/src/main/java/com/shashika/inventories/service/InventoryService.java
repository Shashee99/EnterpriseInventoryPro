package com.shashika.inventories.service;

import com.shashika.inventories.dto.requesDto.InventoryRequestDto;
import com.shashika.inventories.dto.requesDto.InventoryUpdateRequestDto;
import com.shashika.inventories.dto.responseDto.InventoryResponseDto;
import com.shashika.inventories.entity.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventoryService {

    boolean addInvertory(InventoryRequestDto req);
    boolean updateInventory(InventoryUpdateRequestDto req);
    InventoryResponseDto getInventorybyInventoryId(Long inventoryid);
    Page<Inventory> getAllInventoriesbyuserId(Long userid, Pageable pageable);
    void deleteInventory(List<Long> inventoryId);

    Page<Inventory> searchInventory(Long userId,List<String> brands, List<String> types, String description, Pageable pageable);

}
