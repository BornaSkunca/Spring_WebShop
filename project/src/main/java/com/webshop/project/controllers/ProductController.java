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

import com.webshop.project.models.Product;
import com.webshop.project.services.ProductService;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }

    //Some endpoints require login and some require Admin or Seller roles

    @GetMapping("/products")
    public List<Product> getAll(){
        return this.productService.getAll();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Long id){
        return this.productService.getProduct(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','SELLER')")
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product){
        return this.productService.createProduct(product);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/products/{id}")
    public Product updateProduct(@RequestBody Product product,@PathVariable Long id){
        return this.productService.updateProduct(product, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/products/{id}")
    public Product deleteProduct(@RequestBody Product product,@PathVariable Long id){
        return this.productService.deleteProduct(product, id);
    }

}
