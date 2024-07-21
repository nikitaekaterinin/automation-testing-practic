package com.practiseofsoftwaretesting;

import com.practicesoftwaretesting.user.UserSteps;
import com.practicesoftwaretesting.user.model.NewUserRegisterRequest;

import static com.practicesoftwaretesting.user.UserSteps.ADMIN_EMAIL;
import static com.practicesoftwaretesting.user.UserSteps.ADMIN_PASSWORD;

public abstract class BaseTest {

    UserSteps userSteps = new UserSteps();

    public void registerUser(String userEmail, String password) {
        userSteps.registerUser(userEmail, password);
    }

    public String loginUser(String userEmail, String password) {
        return userSteps.loginUser(userEmail, password);
    }

    public String loginAsAdmin() {
        return loginUser(ADMIN_EMAIL, ADMIN_PASSWORD);
    }

    public String registerAndLoginNewUser() {
        return userSteps.registerAndLoginNewUser();
    }

    public NewUserRegisterRequest buildUser(String email, String password) {
        return userSteps.buildUser(email, password);
    }

    public String getUserEmail() {
        return userSteps.getUserEmail();
    }
}