package com.shashika.inventories.dto.requesDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InventoryRequestDto {


    private String brand;
    private String type;
    private String description;
    private Double price;
    private LocalDate expired_on;
}
