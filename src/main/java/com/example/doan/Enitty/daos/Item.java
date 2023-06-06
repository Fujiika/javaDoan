package com.example.doan.Enitty.daos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Long ProductId;
    private String ProductName;
    private Double price;
    private int quantity;
}