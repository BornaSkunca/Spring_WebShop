package com.webshop.project.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartITemDTO {
    private Long productId;
    private String productName;
    private int quantity;
    private Float price;
}
