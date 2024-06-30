package com.practicesoftwaretesting.user;

import com.practicesoftwaretesting.common.BaseController;
import com.practicesoftwaretesting.common.ResponseDecorator;
import com.practicesoftwaretesting.common.ResponseMessage;
import com.practicesoftwaretesting.user.model.NewUserRegisterRequest;
import com.practicesoftwaretesting.user.model.NewUserRegisteredResponse;
import com.practicesoftwaretesting.user.model.UserLoginRequest;

import com.practicesoftwaretesting.user.model.UserLoginResponse;

public class UserController extends BaseController<UserController> {

    public ResponseDecorator<NewUserRegisteredResponse> registerUser(NewUserRegisterRequest registerUserRequest) {
        return new ResponseDecorator<>(
                baseClient()
                        .body(registerUserRequest)
                        .post("/users/register"),
                NewUserRegisteredResponse.class
        );
    }

    public ResponseDecorator<UserLoginResponse> loginUser(UserLoginRequest loginRequest) {
        return new ResponseDecorator<>(
                baseClient()
                        .body(loginRequest)
                        .post("/users/login"),
                UserLoginResponse.class
        );
    }

    public ResponseDecorator<Void> deleteUser(String userId) {
        return new ResponseDecorator<>(
                baseClient()
                        .delete("users/" + userId),
                Void.class
        );
    }

    public ResponseDecorator<ResponseMessage> getUser(String userId) {
        return new ResponseDecorator<>(
                baseClient()
                        .get("users/" + userId),
                ResponseMessage.class
        );
    }
}