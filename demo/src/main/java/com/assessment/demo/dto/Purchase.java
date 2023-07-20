package com.assessment.demo.dto;

import com.assessment.demo.entities.Cart;
import com.assessment.demo.entities.CartItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Cart cart;
    private Set<CartItem> cartItems;
}
