package com.webshop.project.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    private Long id;
    private LocalDateTime orderDate;
    private float totalPrice;
    private String status;
    private List<OrderItemDTO> items;
}
