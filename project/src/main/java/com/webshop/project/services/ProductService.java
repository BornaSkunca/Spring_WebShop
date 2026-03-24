package com.webshop.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webshop.project.models.Product;
import com.webshop.project.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAll(){
        return this.productRepository.findAll();
    }

    public Product getProduct(Long id){
        Optional<Product> productOpt=this.productRepository.findById(id);

        if(!productOpt.isPresent()){
            return null;
        }

        return productOpt.get();
    }

    public Product createProduct(Product product){
        Product newProduct=this.productRepository.save(product);
        return newProduct;
    }

    public Product updateProduct(Product product,Long id){
        Optional<Product> productOpt=this.productRepository.findById(id);

        if(!productOpt.isPresent()){
            return null;
        }

        Product updatedProduct=this.productRepository.save(productOpt.get());

        return updatedProduct;
    }

    public Product deleteProduct(Product product,Long id){

        Optional<Product> prodOpt=productRepository.findById(id);

        if (!prodOpt.isPresent()) {
            return null;
        }

        productRepository.delete(product);

        return product;

    }
}
