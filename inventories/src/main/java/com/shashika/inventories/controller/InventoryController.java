package com.shashika.inventories.controller;

import com.shashika.inventories.dto.requesDto.InventoryRequestDto;
import com.shashika.inventories.dto.requesDto.InventoryUpdateRequestDto;
import com.shashika.inventories.dto.responseDto.InventoryResponseDto;
import com.shashika.inventories.entity.Inventory;
import com.shashika.inventories.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/inventories")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
//    create
    @PostMapping()
    ResponseEntity<String> addInventory(@RequestBody InventoryRequestDto req){
        if(req.getUser_id() == null){
            return ResponseEntity.badRequest().body("User id is not found");
        } else if (req.getDescription() == null) {
            return ResponseEntity.badRequest().body("Description is not found");
        } else if (req.getBrand() == null) {
            return ResponseEntity.badRequest().body("Brand is not found");
        } else if (req.getExpired_on() == null) {
            return ResponseEntity.badRequest().body("Expiring date is not found");
        } else if (req.getType() == null) {
            return ResponseEntity.badRequest().body("Type is not found");
        } else if (req.getPrice()==null) {
            return ResponseEntity.badRequest().body("Price is not found");
        }
        else{
           boolean result = inventoryService.addInvertory(req);
           if (result){
               return ResponseEntity.ok().body("Inventory added successfully");
           }
           else {
               return ResponseEntity.badRequest().body("Error occured");
           }
        }
    }


//    @GetMapping("/allinventoriesByUser/{id}")
//    ResponseEntity<List<Inventory>> getAllInventories (@PathVariable Long id){
//        return ResponseEntity.ok(inventoryService.getAllInventoriesbyuserId(id));
//    }

    @GetMapping()
    ResponseEntity<?> getInventory(
            @RequestParam(required = false) String operation,
            @RequestParam(required = false) Long userid,
            @RequestParam(required = false) Long invid,
            @RequestParam(required = false) List<String> brands,
            @RequestParam(required = false) List<String> typelist,
            @RequestParam(required = false) String description,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "0") Integer offset
    ) {
        Pageable pageable = PageRequest.of(offset, pageSize);

       if(Objects.equals(operation, "GETUSERINV")){
           if(userid != null){
               return ResponseEntity.ok(inventoryService.getAllInventoriesbyuserId(userid,pageable));    
           }
           else{
               return ResponseEntity.ok("User id not provided");
           }
       } else if (Objects.equals(operation, "GETINVBYID")) {
           return ResponseEntity.ok(inventoryService.getInventorybyInventoryId(invid));

       } else if (Objects.equals(operation, "SEARCH")) {
           Page<Inventory> searchResult = inventoryService.searchInventory(
                   userid,
                   brands,
                   typelist,
                   description,
                   pageable
           );
           return ResponseEntity.ok(searchResult);
       }

           return null;

    }

    @DeleteMapping()
    ResponseEntity<String> deleteInventory(@RequestParam(required = false) List<Long> ids){
        inventoryService.deleteInventory(ids);
        return ResponseEntity.ok("Deleted record");
    }

    @PutMapping()
    ResponseEntity<String> updateInventory(@RequestBody InventoryUpdateRequestDto req){
        boolean result = inventoryService.updateInventory(req);
        if(result){
            return ResponseEntity.ok("Update Successfully");
        }
        else {
           return ResponseEntity.badRequest().body("Fail to update");
        }
    }




}
