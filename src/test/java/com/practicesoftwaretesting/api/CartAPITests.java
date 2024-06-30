package com.practicesoftwaretesting.api;

import com.practicesoftwaretesting.cart.CartController;
import com.practicesoftwaretesting.cart.asserts.CartItemsAsserts;
import com.practicesoftwaretesting.cart.asserts.CreatedCartAsserts;
import com.practicesoftwaretesting.cart.model.AddItemToCartRequest;
import com.practicesoftwaretesting.cart.model.CartItem;

import com.practicesoftwaretesting.common.asserts.ResponseResultAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CartAPITests extends BaseTest {

    private String authToken;

    private static final String PRODUCT_ID = "01J1MTJ4ZBGFESC2HH1A4RQNKW";

    CartController cartController = new CartController();

    @BeforeEach
    void beforeEach() {
        authToken = registerAndLoginNewUser();
    }

    @Test
    void addAndDeleteCart() {
        var createdCart = cartController.withToken(authToken).createCart()
                .assertStatusCode(201)
                .as();
        new CreatedCartAsserts(createdCart)
                .cartIdIsNotNull();

        var cartId = createdCart.getId();
        var addingItemResult = cartController.addItemToCart(cartId, new AddItemToCartRequest(PRODUCT_ID, 4))
                .assertStatusCode(200)
                .as();
        new ResponseResultAssert(addingItemResult)
                .responseResultIsNotNull()
                .responseResultIs("item added or updated");

        var cartOptions = cartController.getCart(cartId)
                .assertStatusCode(200)
                .as();
        var productIds = cartOptions.getCartItems().stream().map(CartItem::getProductId).toList();
        new CartItemsAsserts()
                .checkAddedProduct(productIds, PRODUCT_ID);

        cartController.deleteCart(cartId)
                .assertStatusCode(204);
    }
}