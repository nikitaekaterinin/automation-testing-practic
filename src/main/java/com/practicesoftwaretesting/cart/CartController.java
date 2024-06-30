package com.practicesoftwaretesting.cart;

import com.practicesoftwaretesting.cart.model.AddItemToCartRequest;
import com.practicesoftwaretesting.cart.model.CartOptions;
import com.practicesoftwaretesting.cart.model.CreatedCartResponse;
import com.practicesoftwaretesting.common.BaseController;
import com.practicesoftwaretesting.common.ResponseDecorator;
import com.practicesoftwaretesting.common.responses.ResponseResult;

public class CartController extends BaseController<CartController> {

    public ResponseDecorator<CreatedCartResponse> createCart() {
        return new ResponseDecorator<>(
                baseClient().post("/carts"),
                CreatedCartResponse.class
        );
    }

    public ResponseDecorator<ResponseResult> addItemToCart(String cartId, AddItemToCartRequest cartItem) {
        return new ResponseDecorator<>(
                baseClient()
                        .body(cartItem)
                        .post("/carts/" + cartId),
                ResponseResult.class
        );
    }

    public ResponseDecorator<CartOptions> getCart(String cartId) {
        return new ResponseDecorator<>(
                baseClient()
                        .get("/carts/" + cartId),
                CartOptions.class
        );
    }

    public ResponseDecorator<Void> deleteCart(String cartId) {
        return new ResponseDecorator<>(
                baseClient()
                        .delete("/carts/" + cartId),
                Void.class
        );
    }
}