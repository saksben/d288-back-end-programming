package com.assessment.demo.services;

import com.assessment.demo.dto.Purchase;
import com.assessment.demo.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
