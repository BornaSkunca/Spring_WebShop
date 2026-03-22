package com.webshop.project.mapper;

import org.springframework.stereotype.Component;

import com.webshop.project.DTO.ProductDTO;
import com.webshop.project.models.Product;

@Component
public class ProductMapper {

    public ProductDTO toDTO(Product product){
        if(product==null) return null;
        ProductDTO dto=new ProductDTO();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setCategory(product.getCategory().name());

        return dto;

    }
}
