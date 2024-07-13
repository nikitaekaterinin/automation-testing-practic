package com.practicesoftwaretesting.user;
import com.github.javafaker.Faker;
import com.practicesoftwaretesting.user.model.UserLoginRequest;
import com.practicesoftwaretesting.user.model.NewUserRegisterRequest;

public class UserSteps {

    public static final String DEFAULT_PASSWORD = "12Example#";
    public static final String ADMIN_EMAIL = "admin@practicesoftwaretesting.com";
    public static final String ADMIN_PASSWORD = "welcome01";

    public void registerUser(String userEmail, String password) {
        var userController = new UserController();
        var registerUserRequest = buildUser(userEmail, password);
        userController.registerUser(registerUserRequest).as();
    }

    public String loginUser(String userEmail, String password) {
        var userController = new UserController();
        var userLoginResponse = userController.loginUser(new UserLoginRequest(userEmail, password))
                .as();
        return userLoginResponse.getAccessToken();
    }

    public String registerAndLoginNewUser() {
        var userEmail = getUserEmail();
        registerUser(userEmail, DEFAULT_PASSWORD);
        return loginUser(userEmail, DEFAULT_PASSWORD);
    }

    public String loginAsAdmin() {
        return loginUser(ADMIN_EMAIL, ADMIN_PASSWORD);
    }

    public NewUserRegisterRequest buildUser(String email, String password) {
        return NewUserRegisterRequest.builder()
                .firstName("John")
                .lastName("Lennon")
                .address("Street 1")
                .city("City")
                .state("State")
                .country("Country")
                .postcode("1234AA")
                .phone("0987654321")
                .dob("1941-01-01")
                .password(password)
                .email(email)
                .build();
    }

    public String getUserEmail() {
        return Faker.instance()
                .friends()
                .character()
                .toLowerCase()
                .replaceAll(" ", "") + "@gmail.com";
    }
}