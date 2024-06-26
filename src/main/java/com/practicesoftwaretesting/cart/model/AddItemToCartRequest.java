package com.practicesoftwaretesting.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddItemToCartRequest {

    private String productId;
    private int quantity;
}