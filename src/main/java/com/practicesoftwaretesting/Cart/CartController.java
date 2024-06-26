package com.practicesoftwaretesting.Cart;

import com.practicesoftwaretesting.Cart.Model.AddItemToCartRequest;
import com.practicesoftwaretesting.Common.BaseController;
import io.restassured.response.Response;

public class CartController extends BaseController {

    public Response createCart() {
        return baseClient()
                .post("/carts");
    }

    public Response addItemToCart(String cartId, AddItemToCartRequest itemToAdd) {
        return baseClient()
                .body(itemToAdd)
                .post("/carts/" + cartId);
    }

    public Response getCart(String cartId) {
        return baseClient()
                .get("/carts/" + cartId);
    }

    public Response deleteCart(String cartId) {
        return baseClient()
                .delete("/carts/" + cartId);
    }
}