package com.webshop.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webshop.project.DTO.CreateProductRequest;
import com.webshop.project.DTO.UpdateProductRequest;
import com.webshop.project.exception.NotFoundException;
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

    public Product createProduct(CreateProductRequest request){
        Product product=new Product();

        product.setCategory(request.getCategory());
        product.setDescription(request.getDescription());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setSeller(request.getSeller());

        Product newProduct=this.productRepository.save(product);
        return newProduct;
    }

    public Product updateProduct(UpdateProductRequest request,Long id){
        Product productSearch=this.productRepository.findById(id)
        .orElseThrow(()->new NotFoundException("Product not found!"));


        Product updatedProduct= new Product();
        updatedProduct.setId(id);
        updatedProduct.setDescription(request.getDescription());
        updatedProduct.setCategory(request.getCategory());
        updatedProduct.setName(request.getName());
        updatedProduct.setPrice(request.getPrice());
        updatedProduct.setSeller(request.getSeller());

        this.productRepository.save(updatedProduct);

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
