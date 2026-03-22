package com.webshop.project.DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {
    private Long id;
    private List<CartITemDTO> items;
    private double totalPrice;
}
