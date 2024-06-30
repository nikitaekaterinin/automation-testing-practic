package com.practicesoftwaretesting.cart.asserts;

import com.practicesoftwaretesting.cart.model.CreatedCartResponse;
import lombok.AllArgsConstructor;

import static org.assertj.core.api.Assertions.*;

@AllArgsConstructor
public class CreatedCartAsserts {

    private CreatedCartResponse newCartResponse;

    public CreatedCartAsserts cartIdIsNotNull() {
        assertThat(newCartResponse.getId())
                .withFailMessage("Cart ID should not be null")
                .isNotNull();
        return this;
    }
}