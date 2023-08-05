package com.assessment.demo.services;

import com.assessment.demo.entities.Cart;
import com.assessment.demo.entities.CartItem;
import com.assessment.demo.entities.Customer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
public class Purchase {

    private Customer customer;
    private Cart cart;
    private Set<CartItem> cartItems;
}
