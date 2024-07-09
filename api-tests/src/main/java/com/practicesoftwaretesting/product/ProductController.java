package com.practicesoftwaretesting.product;

import com.practicesoftwaretesting.common.BaseController;
import com.practicesoftwaretesting.common.ResponseDecorator;
import com.practicesoftwaretesting.product.model.ProductRequest;
import com.practicesoftwaretesting.product.model.ProductResponse;

public class ProductController extends BaseController<ProductController> {

    public ResponseDecorator<ProductResponse> getProducts(ProductRequest productsRequest) {
        return new ResponseDecorator<>(
                baseClient()
                        .queryParam("by_brand", productsRequest.getBrand())
                        .queryParam("by_category", productsRequest.getCategory())
                        .queryParam("is_rental", productsRequest.isRental())
                        .queryParam("between", productsRequest.getBetween())
                        .queryParam("sort", productsRequest.getSort())
                        .queryParam("page", productsRequest.getPage())
                        .get("/products"),
                ProductResponse.class
        );
    }
}