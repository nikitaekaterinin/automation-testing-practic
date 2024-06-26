package com.practicesoftwaretesting.APItests;

import com.practicesoftwaretesting.Cart.CartController;
import com.practicesoftwaretesting.Cart.Model.AddItemToCartRequest;
import com.practicesoftwaretesting.Cart.Model.CartItem;
import com.practicesoftwaretesting.Cart.Model.CartOptions;
import com.practicesoftwaretesting.Cart.Model.CreatedCartResponse;
import com.practicesoftwaretesting.Common.ResponseResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartAPITests extends BaseTest{

    private static final String PRODUCT_ID = "01J0VCWHNSRYKW2QVNWHJ1N3F3";

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