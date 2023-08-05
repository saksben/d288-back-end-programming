package com.assessment.demo.services;

import com.assessment.demo.dao.CartItemRepository;
import com.assessment.demo.dao.CartRepository;
import com.assessment.demo.dao.CustomerRepository;
import com.assessment.demo.entities.Cart;
import com.assessment.demo.entities.CartItem;
import com.assessment.demo.entities.Customer;
import com.assessment.demo.entities.StatusType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;

    public CheckoutServiceImpl(
            CustomerRepository customerRepository,
                               CartRepository cartRepository,
            CartItemRepository cartItemRepository
                               ) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the cart info from dto
        Cart cart = purchase.getCart();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        // populate cart with cartItems
        Set<CartItem> cartItems = purchase.getCartItems();
        cartItems.forEach(item -> item.setCart(cart));
        cartItems.forEach(item -> cart.add(item));


        // save cart to the database
        cart.setStatus(StatusType.ordered);
        cartRepository.save(cart);
//        cartItems.forEach(item -> System.out.println(item));

        //populate customer with cart
        Customer customer = purchase.getCustomer();
        customer.add(cart);

        // save customer to the database
        // for some reason the tracking number doesn't populate unless this is commented out
//        customerRepository.save(customer);

        // return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {

        // generate a random UUID number (UUID version-4)
        return UUID.randomUUID().toString();

    }
}
