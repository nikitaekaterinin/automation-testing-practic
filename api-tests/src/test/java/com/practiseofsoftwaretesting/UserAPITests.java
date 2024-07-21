package com.practiseofsoftwaretesting;

import com.practicesoftwaretesting.common.asserts.ResponseMessageAssert;
import com.practicesoftwaretesting.user.asserts.LoginUserAsserts;
import com.practicesoftwaretesting.user.asserts.UserRegistrationAsserts;
import com.practicesoftwaretesting.user.model.*;
import com.practicesoftwaretesting.user.UserController;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class UserAPITests extends BaseTest {

    private String userEmail;
    private String userId;

    UserController userController = new UserController();

    @Test
    void testUserCreateDelete() {
        userEmail = getUserEmail();

        var expectedUser = buildUser(userEmail, defaultPassword);
        var registeredUserResponse = userController.registerUser(expectedUser)
                .assertStatusCode(201)
                .as();
        new UserRegistrationAsserts(registeredUserResponse)
                .createdAtIsNotNull()
                .firstNameIs(expectedUser.getFirstName())
                .lastNameIs(expectedUser.getLastName())
                .countryIs(expectedUser.getCountry())
                .phoneIs(expectedUser.getPhone())
                .cityIs(expectedUser.getCity())
                .addressIs(expectedUser.getAddress());

        var loginRequestBody = new UserLoginRequest(userEmail, defaultPassword);
        var userLoginResponse = userController.loginUser(loginRequestBody)
                .assertStatusCode(200)
                .as();
        new LoginUserAsserts(userLoginResponse)
                .isNotExpired()
                .accessTokenIsNotNull()
                .tokenTypeIs("bearer");

        userId = registeredUserResponse.getId();
    }

    @AfterEach
    void deleteUser() {
        var token = loginAsAdmin();
        userController.withToken(token).deleteUser(userId)
                .assertStatusCode(204);

        var messageAfterDelete = userController.withToken(token).getUser(userId)
                .assertStatusCode(404)
                .as();
        new ResponseMessageAssert(messageAfterDelete)
                .userNotFound("Requested item not found");
    }
}