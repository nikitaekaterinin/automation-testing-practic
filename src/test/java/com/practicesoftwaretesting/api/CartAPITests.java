package com.practicesoftwaretesting.api;

import com.practicesoftwaretesting.cart.CartController;
import com.practicesoftwaretesting.cart.model.AddItemToCartRequest;
import com.practicesoftwaretesting.cart.model.CartItem;
import com.practicesoftwaretesting.cart.model.CartOptions;
import com.practicesoftwaretesting.cart.model.CreatedCartResponse;
import com.practicesoftwaretesting.common.ResponseResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartAPITests extends BaseTest{

    private static final String PRODUCT_ID = "01J1AGZ9F737CQEK7HX4BM1G1T";

    CartController cartController = new CartController();

    @Test
    void addAndDeleteCart(){
        var createdCart = cartController.createCart()
                .as(CreatedCartResponse.class);
        assertNotNull(createdCart.getId());

       var cartId = createdCart.getId();
       var addingItemResult = cartController.addItemToCart(cartId, new AddItemToCartRequest(PRODUCT_ID, 4))
               .as(ResponseResult.class);
       assertNotNull(addingItemResult.getResult());
       assertEquals("item added or updated",addingItemResult.getResult());

        var cartOptions = cartController.getCart(cartId)
                .as(CartOptions.class);
        var productIds = cartOptions.getCartItems().stream().map(CartItem::getProductId).toList();
        assertTrue(productIds.contains(PRODUCT_ID));

        cartController.deleteCart(cartId)
                .then()
                .statusCode(204);
    }
}