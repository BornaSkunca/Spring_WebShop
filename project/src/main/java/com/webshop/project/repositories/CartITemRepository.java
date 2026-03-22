package com.webshop.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.project.models.CartITem;

public interface CartITemRepository extends JpaRepository<CartITem,Long>{

}
