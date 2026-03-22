package com.webshop.project.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.project.DTO.CartDTO;
import com.webshop.project.DTO.CartITemDTO;
import com.webshop.project.DTO.OrderDTO;
import com.webshop.project.mapper.CartMapper;
import com.webshop.project.mapper.OrderMapper;
import com.webshop.project.models.Cart;
import com.webshop.project.models.CartITem;
import com.webshop.project.models.Order;
import com.webshop.project.services.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;
    private final OrderMapper orderMapper;

    @PreAuthorize("hasRole('REGULAR')")
    @GetMapping
    public CartDTO getCart(){
        Cart cart=cartService.getCart();
        return cartMapper.toDTO(cart);
    }

    @PostMapping("/add/{productId}")
    @PreAuthorize("hasRole('REGULAR')")
    public CartDTO addProduct(@PathVariable Long productId){
        Cart cart=cartService.addProduct(productId);
        return cartMapper.toDTO(cart);
    }

    @PreAuthorize("hasRole('REGULAR')")
    @DeleteMapping("/remove/{id}")
    public CartITemDTO removeItem(@PathVariable Long id){
        CartITem item=cartService.removeItem(id);
        return cartMapper.mapItem(item);
    }

    @PreAuthorize("hasRole('REGULAR')")
    @PutMapping("/update/{itemId}")
    public CartDTO updateQuantity(@RequestParam int quantity,@PathVariable Long itemId){
        Cart cart=cartService.updateQuantity(quantity, itemId);
        return cartMapper.toDTO(cart);
    }

    @PreAuthorize("hasRole('REGULAR')")
    @PostMapping("checkout")
    public OrderDTO checkout(){
        Order order=cartService.checkout();
        return orderMapper.toDTO(order);
    }
}
