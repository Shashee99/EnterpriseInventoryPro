package com.shashika.inventories.dto.responseDto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InventoryResponseDto {

    private Long id;

    @Column(name = "user_id")
    private Long user_id;
    private String brand;
    private String type;
    private String description;
    private Double price;
    private LocalDate expired_on;

}
