package com.example.library.dto;

import com.example.library.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NegativeOrZero;
//cette classe permet Cela facilite le transfert de ces données entre la couche de présentation,
// la couche métier et éventuellement la couche d'accès aux données.et elle contient juste les donnes qu'on va
//utiliser
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private double costPrice;
    private double salePrice;
    private int currentQuantity;
    private Category category;
    private String image;
    private boolean activated;
    private boolean deleted;
}