package com.practicesoftwaretesting.api;

import com.practicesoftwaretesting.user.model.*;
import com.practicesoftwaretesting.user.UserController;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserAPITests extends BaseTest {

    private String userEmail;

    UserController userController = new UserController();

    @Test
    void testUserCreateDelete() {
        userEmail = getUserEmail();

        var requestRegisterBody = buildUser(userEmail, DEFAULT_PASSWORD);
        var registeredUserResponse = userController.registerUser(requestRegisterBody)
                .assertStatusCode(201)
                .as();
        assertEquals("Kate", registeredUserResponse.getFirstName());
        assertNotNull(registeredUserResponse.getId());

        var requestUserLoginBody = new UserLoginRequest(userEmail, DEFAULT_PASSWORD);
        var userLoginResponse = userController.loginUser(requestUserLoginBody)
                .assertStatusCode(200)
                .as();
        assertNotNull(userLoginResponse.getAccessToken());

        var adminLoginRequestBody = new UserLoginRequest("admin@practicesoftwaretesting.com", "welcome01");
        var adminloginResponse = userController.loginUser(adminLoginRequestBody)
                .assertStatusCode(200)
                .as();
        assertNotNull(adminloginResponse.getAccessToken());

        var userId = registeredUserResponse.getId();
        var token = adminloginResponse.getAccessToken();

        userController.withToken(token).deleteUser(userId)
                .assertStatusCode(204);

        var messageAfterDelete = userController.withToken(token).getUser(userId)
                .assertStatusCode(404)
                .as();
        assertEquals("Requested item not found", messageAfterDelete.getMessage());
    }
}