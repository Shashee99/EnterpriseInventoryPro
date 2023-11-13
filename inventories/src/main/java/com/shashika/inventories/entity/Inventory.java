package com.shashika.inventories.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory {
//Brand, Type, Description, Price, Expired On
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long user;
    private String brand;
    private String type;
    private String description;
    private Double price;
    private LocalDate expired_on;


}
