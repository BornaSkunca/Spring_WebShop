package com.webshop.project.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.webshop.project.DTO.CartDTO;
import com.webshop.project.DTO.CartITemDTO;
import com.webshop.project.models.Cart;
import com.webshop.project.models.CartITem;

@Component
public class CartMapper {

    public CartDTO toDTO(Cart cart){

        if (cart==null) return null;

        CartDTO dto=new CartDTO();
        dto.setId(cart.getId());

        List<CartITemDTO> items=cart.getItems().stream().map(this::mapItem).toList();

        dto.setItems(items);

        double total=items.stream().mapToDouble(i->i.getPrice()*i.getQuantity()).sum();

        dto.setTotalPrice(total);

        return dto;
    }

    public CartITemDTO mapItem(CartITem item){

        CartITemDTO dto=new CartITemDTO();

        dto.setProductId(item.getId());
        dto.setPrice(item.getProduct().getPrice());
        dto.setProductName(item.getProduct().getName());
        dto.setQuantity(item.getQuantity());

        return dto;
    }
}
