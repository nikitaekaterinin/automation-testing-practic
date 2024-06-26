package com.practicesoftwaretesting.Cart.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddItemToCartRequest {

    private String productId;
    private int quantity;
}