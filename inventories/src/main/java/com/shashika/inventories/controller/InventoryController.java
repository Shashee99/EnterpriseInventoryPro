package com.shashika.inventories.controller;

import com.shashika.inventories.dto.requesDto.InventoryRequestDto;
import com.shashika.inventories.dto.requesDto.InventoryUpdateRequestDto;
import com.shashika.inventories.dto.responseDto.InventoryResponseDto;
import com.shashika.inventories.dto.responseDto.ResponseDto;
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
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("inventories")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
//    create
    @PostMapping()
    ResponseEntity<?> addInventory(@RequestBody InventoryRequestDto req){
        if (req.getDescription() == null) {
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
               return ResponseEntity.ok().body(new ResponseDto("added succesfully"));
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

            @RequestParam(required = false) Long invid,
            @RequestParam(required = false) List<String> brands,
            @RequestParam(required = false) List<String> typelist,
            @RequestParam(required = false) String description,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "0") Integer offset
    ) {
        Pageable pageable = PageRequest.of(offset, pageSize);

       if(Objects.equals(operation, "GETALL")) {

           return ResponseEntity.ok(inventoryService.getAllInventories(pageable));

       } else if (Objects.equals(operation, "SEARCH")) {
           Page<Inventory> searchResult = inventoryService.searchInventory(
                   brands,
                   typelist,
                   description,
                   pageable
           );
           return ResponseEntity.ok(searchResult);
       }

           return null;

    }


    @GetMapping("/{id}")
    ResponseEntity<?> getInventorybyid(@PathVariable Long id){
        return ResponseEntity.ok(inventoryService.getInventorybyInventoryId(id));
    }



    @DeleteMapping()
    ResponseEntity<?> deleteInventory(@RequestParam(required = false) List<Long> ids){
//        System.out.println(ids);
        inventoryService.deleteInventory(ids);
        return ResponseEntity.ok(new ResponseDto("User deleted!"));
    }

    @PutMapping()
    ResponseEntity<?> updateInventory(@RequestBody InventoryUpdateRequestDto req){
        boolean result = inventoryService.updateInventory(req);
        if(result){
            return ResponseEntity.ok(new ResponseDto("Updated successfully"));
        }
        else {
           return ResponseEntity.badRequest().body(new ResponseDto("Failed to update"));
        }
    }




}
