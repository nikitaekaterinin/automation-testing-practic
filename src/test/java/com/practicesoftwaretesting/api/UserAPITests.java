package com.practicesoftwaretesting.api;

import com.github.javafaker.Faker;
import com.practicesoftwaretesting.common.ResponseMessage;
import com.practicesoftwaretesting.user.model.*;
import com.practicesoftwaretesting.user.UserController;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserAPITests extends BaseTest {

    private static final String USER_PASSWORD = "NanaJJ1929##";
    private String userEmail;

    UserController userController = new UserController();

    @Test
    void testUserCreateDelete() {
        userEmail = getUserEmail();

        var requestRegisterBody = buildUser();
        var registeredUserResponse = userController.registerUser(requestRegisterBody)
                .as(NewUserRegisteredResponse.class);
        assertEquals("Kate", registeredUserResponse.getFirstName());
        assertNotNull(registeredUserResponse.getId());

        var requestUserLoginBody = new UserLoginRequest(userEmail, USER_PASSWORD);
        var userLoginResponse = userController.loginUser(requestUserLoginBody)
                .as(UserLoginResponse.class);
        assertNotNull(userLoginResponse.getAccessToken());

        var adminLoginRequestBody = new UserLoginRequest("admin@practicesoftwaretesting.com", "welcome01");
        var adminloginResponse = userController.loginUser(adminLoginRequestBody)
                .as(UserLoginResponse.class);
        assertNotNull(adminloginResponse.getAccessToken());

        var userId = registeredUserResponse.getId();
        var token = adminloginResponse.getAccessToken();

        userController.deleteUser(userId, token)
                .then()
                .statusCode(204);

        var messageAfterDelete = userController.getUser(userId, token)
                .as(ResponseMessage.class);
        assertEquals("Requested item not found", messageAfterDelete.getMessage());
    }

    private NewUserRegisterRequest buildUser() {
        return NewUserRegisterRequest.builder()
                .firstName("Kate")
                .lastName("Tester")
                .address("Street 1")
                .city("City")
                .state("State")
                .country("Country")
                .postcode("1234AA")
                .phone("0987654321")
                .dob("1941-01-01")
                .password(USER_PASSWORD)
                .email(userEmail)
                .build();
    }

    private String getUserEmail() {
        return Faker.instance()
                .friends()
                .character()
                .toLowerCase()
                .replaceAll(" ", "") + "@gmail.com";
    }
}