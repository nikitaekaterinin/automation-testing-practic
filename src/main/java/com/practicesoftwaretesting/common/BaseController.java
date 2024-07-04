package com.practicesoftwaretesting.common;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;

public abstract class BaseController<T> {

    private String authToken;

    private static final String BASE_URI = "https://api.practicesoftwaretesting.com";

    protected RequestSpecification baseClient() {
        var requestSpecification = RestAssured.given()
                .baseUri(BASE_URI)
                .contentType(JSON);
        if (authToken != null) {
            requestSpecification.header("Authorization", "Bearer" + authToken);
        }
        return requestSpecification;
    }

    public T withToken(String token) {
        this.authToken = token;
        return (T) this;
    }

    public void cleanToken() {
        this.authToken = null;
    }
}