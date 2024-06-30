package com.practicesoftwaretesting.api;

import com.practicesoftwaretesting.common.asserts.NotFoundResponseAssert;
import com.practicesoftwaretesting.user.asserts.LoginUserAsserts;
import com.practicesoftwaretesting.user.asserts.UserRegistrationAsserts;
import com.practicesoftwaretesting.user.model.*;
import com.practicesoftwaretesting.user.UserController;

import org.junit.jupiter.api.Test;

public class UserAPITests extends BaseTest {

    private String userEmail;

    UserController userController = new UserController();

    @Test
    void testUserCreateDelete() {
        userEmail = getUserEmail();

        var expectedUser = buildUser(userEmail, DEFAULT_PASSWORD);
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

        var requestUserLoginBody = new UserLoginRequest(userEmail, DEFAULT_PASSWORD);
        var userLoginResponse = userController.loginUser(requestUserLoginBody)
                .assertStatusCode(200)
                .as();
        new LoginUserAsserts(userLoginResponse)
                .isNotExpired()
                .accessTokenIsNotNull()
                .tokenTypeIs("bearer");

        var adminLoginRequestBody = new UserLoginRequest("admin@practicesoftwaretesting.com", "welcome01");
        var adminloginResponse = userController.loginUser(adminLoginRequestBody)
                .assertStatusCode(200)
                .as();
        new LoginUserAsserts(adminloginResponse)
                .isNotExpired()
                .accessTokenIsNotNull()
                .tokenTypeIs("bearer");

        var userId = registeredUserResponse.getId();
        var token = adminloginResponse.getAccessToken();

        userController.withToken(token).deleteUser(userId)
                .assertStatusCode(204);

        var messageAfterDelete = userController.withToken(token).getUser(userId)
                .assertStatusCode(404)
                .as();
        new NotFoundResponseAssert(messageAfterDelete)
                .userNotFound("Requested item not found");
    }
}