package com.practicesoftwaretesting.api;

import com.practicesoftwaretesting.cart.CartController;
import com.practicesoftwaretesting.cart.model.AddItemToCartRequest;
import com.practicesoftwaretesting.cart.model.CartItem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartAPITests extends BaseTest {

    private String authToken;

    private static final String PRODUCT_ID = "01J1CS2DXFCZ39TKZE797F4JQZ";

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
        assertNotNull(createdCart.getId());

        var cartId = createdCart.getId();
        var addingItemResult = cartController.addItemToCart(cartId, new AddItemToCartRequest(PRODUCT_ID, 4))
                .assertStatusCode(200)
                .as();
        assertNotNull(addingItemResult.getResult());
        assertEquals("item added or updated", addingItemResult.getResult());

        var cartOptions = cartController.getCart(cartId)
                .assertStatusCode(200)
                .as();
        var productIds = cartOptions.getCartItems().stream().map(CartItem::getProductId).toList();
        assertTrue(productIds.contains(PRODUCT_ID));

        cartController.deleteCart(cartId)
                .assertStatusCode(204);
    }
}