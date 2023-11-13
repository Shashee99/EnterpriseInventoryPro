package com.shashika.inventories.service;

import com.shashika.inventories.dto.requesDto.InventoryRequestDto;
import com.shashika.inventories.dto.requesDto.InventoryUpdateRequestDto;
import com.shashika.inventories.dto.responseDto.InventoryResponseDto;
import com.shashika.inventories.entity.Inventory;
import com.shashika.inventories.repository.InventoryRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public boolean addInvertory(InventoryRequestDto req) {

        Inventory inventory = new Inventory();
        inventory.setType(req.getType());
        inventory.setPrice(req.getPrice());
        inventory.setUser(req.getUser_id());
        inventory.setExpired_on(req.getExpired_on());
        inventory.setDescription(req.getDescription());
        inventory.setBrand(req.getBrand());

        inventoryRepository.save(inventory);

        return true;
    }
//    @PostConstruct
//    public void addSampleData() {
//        // Add 100 sample data with user id 1
//        for (int i = 0; i < 100; i++) {
//            Inventory inventory = new Inventory();
//            inventory.setUser(1L);
//            inventory.setBrand("SampleBrand" + i);
//            inventory.setType("SampleType" + i);
//            inventory.setDescription("SampleDescription" + i);
//            inventory.setPrice(10.0 + i);
//            inventory.setExpired_on(LocalDate.now().plusDays(i));
//
//            inventoryRepository.save(inventory);
//        }
//    }

    @Override
    public boolean updateInventory(InventoryUpdateRequestDto req) {

        Optional<Inventory> existingInv = inventoryRepository.findById(req.getId());
        if(existingInv.isPresent()){
            existingInv.get().setType(req.getType());
            existingInv.get().setPrice(req.getPrice());
            existingInv.get().setExpired_on(req.getExpired_on());
            existingInv.get().setDescription(req.getDescription());
            existingInv.get().setBrand(req.getBrand());

            inventoryRepository.save(existingInv.get());
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public InventoryResponseDto getInventorybyInventoryId(Long inventoryid) {
        Optional<Inventory> existingInv = inventoryRepository.findById(inventoryid);
        if(existingInv.isPresent()){
            InventoryResponseDto inv = new InventoryResponseDto();
            inv.setUser_id(existingInv.get().getUser());
            inv.setDescription(existingInv.get().getDescription());
            inv.setType(existingInv.get().getType());
            inv.setExpired_on(existingInv.get().getExpired_on());
            inv.setBrand(existingInv.get().getBrand());
            inv.setId(existingInv.get().getId());
            inv.setPrice(existingInv.get().getPrice());
            return inv;
        }
        else{
            return null;
        }
    }

    @Override
    public Page<Inventory> getAllInventoriesbyuserId(Long userid, Pageable pageable) {

        return inventoryRepository.findAllByUser(userid,pageable);
    }

    @Override
    public void deleteInventory(List<Long> inventoryId) {
        for(Long invid : inventoryId){
            Optional<Inventory> existingInv = inventoryRepository.findById(invid);

            inventoryRepository.delete(existingInv.get());
        }


    }

    @Override
    public Page<Inventory> searchInventory(Long userId, List<String> brands, List<String> types, String description, Pageable pageable) {
        return inventoryRepository.search(
                userId,
                brands == null || brands.isEmpty() ? null : brands,
                types == null || types.isEmpty() ? null : types,
                StringUtils.isEmpty(description) ? null : description.toLowerCase(),
                pageable
        );
    }
}
