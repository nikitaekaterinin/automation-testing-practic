package com.practicesoftwaretesting.cart.asserts;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CartItemsAsserts {

    public CartItemsAsserts checkAddedProduct(List<String> productIds, String productId) {
        assertThat(productIds)
                .withFailMessage("Cart should contain the product with ID: %s", productId)
                .contains(productId);
        return this;
    }
}