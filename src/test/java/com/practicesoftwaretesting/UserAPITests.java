package com.practicesoftwaretesting;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.*;

public class UserAPITests extends BaseTest {

    private static final String USER_PASSWORD = "NanaJJ1929##";
    private String userEmail;

    @Test
    void testUserCreateDelete() {
        userEmail = getUserEmail();

        var requestRegisterBody = buildUser();
        var registeredUserResponse = RestAssured.given()
                .contentType(JSON)
                .body(requestRegisterBody)
                .post("/users/register")
                .as(NewUserRegisteredResponse.class);
        assertEquals("Kate",registeredUserResponse.getFirstName());
        assertNotNull(registeredUserResponse.getId());

        var requestUserLoginBody = new UserLoginRequest(userEmail, USER_PASSWORD);
        var userLoginResponse = RestAssured.given()
                .contentType(JSON)
                .body(requestUserLoginBody)
                .post("/users/login")
                .as(UserLoginResponse.class);
        assertNotNull(userLoginResponse.getAccessToken());

        var adminLoginRequestBody = new UserLoginRequest("admin@practicesoftwaretesting.com", "welcome01");
        var adminloginResponse = RestAssured.given()
                .contentType(JSON)
                .body(adminLoginRequestBody)
                .post("/users/login")
                .as(UserLoginResponse.class);
        assertNotNull(adminloginResponse.getAccessToken());

        var userId = registeredUserResponse.getId();
        var token = adminloginResponse.getAccessToken();

        RestAssured.given()
                .header("Authorization", "Bearer" + token)
                .delete("/users/" + userId)
                .then()
                .statusCode(204);

        var messageAfterDelete = RestAssured.given()
                .header("Authorization", "Bearer" + token)
                .get("/users/" + userId)
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