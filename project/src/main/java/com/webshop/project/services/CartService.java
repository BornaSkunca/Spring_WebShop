package com.webshop.project.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.webshop.project.enums.Status;
import com.webshop.project.models.Cart;
import com.webshop.project.models.CartITem;
import com.webshop.project.models.Order;
import com.webshop.project.models.OrderItem;
import com.webshop.project.models.Product;
import com.webshop.project.models.User;
import com.webshop.project.repositories.CartITemRepository;
import com.webshop.project.repositories.CartRepository;
import com.webshop.project.repositories.OrderRepository;
import com.webshop.project.repositories.ProductRepository;
import com.webshop.project.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartITemRepository cartITemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    private User getCurrentUser(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

        String username=authentication.getName();

        return userRepository.findByUsername(username).orElseThrow();
    }

    private Cart getOrCreateCart(User user){
        return cartRepository.findByUser(user).orElseGet(()->{
            Cart cart=new Cart();
            cart.setUser(user);
            return cartRepository.save(cart);
        });
    }

    public Cart addProduct(Long productId){

        User user=getCurrentUser();

        Cart cart=getOrCreateCart(user);

        Product product=productRepository.findById(productId).orElseThrow();

        Optional<CartITem> existingItem=cart.getItems()
        .stream().filter(item->item.getProduct().getId().equals(productId)).findFirst();

        if(existingItem.isPresent()){
            CartITem item=existingItem.get();
            item.setQuantity(item.getQuantity()+1);
            cartITemRepository.save(item);
        }else{
            CartITem cartITem=new CartITem();
            cartITem.setCart(cart);
            cartITem.setProduct(product);
            cartITem.setQuantity(1);

            cartITemRepository.save(cartITem);
        }

        return cartRepository.findByUser(user).orElseThrow();

    }

    public Cart getCart(){
        User user=getCurrentUser();

        return cartRepository.findByUser(user).orElseThrow();
    }

    public CartITem removeItem(Long itemId){
        Optional<CartITem> itemOpt=cartITemRepository.findById(itemId);

        if(!itemOpt.isPresent()){
            return null;
        }

        CartITem item=itemOpt.get();

        cartITemRepository.delete(item);

        return item;
    }

    public Cart updateQuantity(int quantity,Long itemId){

        CartITem item=cartITemRepository.findById(itemId).orElseThrow();

        item.setQuantity(quantity);

        cartITemRepository.save(item);

        return item.getCart();
    }

    public Order checkout(){

        User user=getCurrentUser();
        Cart cart=getCart();

        Order order=new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Status.PENDING);

        List<OrderItem> orderItems=new ArrayList<>();

        double total=0;

        for(CartITem cartITem:cart.getItems()){
            OrderItem orderItem=new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartITem.getProduct());
            orderItem.setQuantity(cartITem.getQuantity());
            orderItem.setPrice(cartITem.getProduct().getPrice());

            total+=cartITem.getProduct().getPrice()*cartITem.getQuantity();

            orderItems.add(orderItem);

        }

        order.setOrderItems(orderItems);

        order.setTotalPrice((float)total);

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order savedOrder=orderRepository.save(order);

        cart.getItems().clear();
        cartRepository.save(cart);

        //cartITemRepository.deleteAll(cart.getItems());

        return savedOrder;
    }
}
