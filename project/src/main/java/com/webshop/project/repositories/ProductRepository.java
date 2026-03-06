package com.webshop.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.project.models.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

}
