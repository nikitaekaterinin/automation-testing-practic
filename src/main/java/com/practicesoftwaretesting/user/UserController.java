package com.practicesoftwaretesting.user;

import com.practicesoftwaretesting.common.BaseController;
import com.practicesoftwaretesting.user.model.NewUserRegisterRequest;
import com.practicesoftwaretesting.user.model.UserLoginRequest;

import io.restassured.response.Response;

public class UserController extends BaseController {

    public Response registerUser(NewUserRegisterRequest registerUserRequest) {
        return baseClient()
                .body(registerUserRequest)
                .post("/users/register");
    }

    public Response loginUser(UserLoginRequest loginRequest) {
        return baseClient()
                .body(loginRequest)
                .post("/users/login");
    }

    public Response deleteUser(String userId, String token) {
        return baseClient()
                .header("Authorization", "Bearer " + token)
                .delete("users/" + userId);
    }

    public Response getUser(String userId, String token) {
        return baseClient()
                .header("Authorization", "Bearer" + token)
                .get("/users/" + userId);
    }
}