package com.practicesoftwaretesting.User;

import com.practicesoftwaretesting.Common.BaseController;
import com.practicesoftwaretesting.User.Model.NewUserRegisterRequest;
import com.practicesoftwaretesting.User.Model.UserLoginRequest;

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