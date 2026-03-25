package com.webshop.project.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.project.DTO.CreateProductRequest;
import com.webshop.project.DTO.ProductDTO;
import com.webshop.project.DTO.UpdateProductRequest;
import com.webshop.project.mapper.ProductMapper;
import com.webshop.project.models.Product;
import com.webshop.project.services.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    //Some endpoints require login and some require Admin or Seller roles

    @GetMapping("/products")
    public List<ProductDTO> getAll(){
        List<Product> products=this.productService.getAll();
        return products.stream().map(product->productMapper.toDTO(product)).toList();
    }

    @GetMapping("/products/{id}")
    public ProductDTO getProduct(@PathVariable Long id){
        Product product=this.productService.getProduct(id);
        return productMapper.toDTO(product);
    }

    @PreAuthorize("hasAnyRole('ADMIN','SELLER')")
    @PostMapping("/products")
    public ProductDTO createProduct(@Valid @RequestBody CreateProductRequest request){
        Product returnProduct=this.productService.createProduct(request);
        return productMapper.toDTO(returnProduct);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/products/{id}")
    public ProductDTO updateProduct(@Valid @RequestBody UpdateProductRequest request,@PathVariable Long id){
        Product returnProduct=this.productService.updateProduct(request, id);
        return productMapper.toDTO(returnProduct);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/products/{id}")
    public ProductDTO deleteProduct(@RequestBody Product product,@PathVariable Long id){
        Product returnProduct=this.productService.deleteProduct(product, id);
        return productMapper.toDTO(returnProduct);
    }

}
