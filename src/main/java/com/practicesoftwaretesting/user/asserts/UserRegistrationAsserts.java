package com.practicesoftwaretesting.user.asserts;

import com.practicesoftwaretesting.user.model.NewUserRegisteredResponse;
import lombok.AllArgsConstructor;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AllArgsConstructor
public class UserRegistrationAsserts {
    private NewUserRegisteredResponse registerUserResponse;

    public UserRegistrationAsserts firstNameIs(String expectedName) {
        assertThat(registerUserResponse.getFirstName())
                .withFailMessage(String.format("firstName should be %s but was %s",
                        expectedName,
                        registerUserResponse.getFirstName()))
                .isEqualTo(expectedName);
        return this;
    }

    public UserRegistrationAsserts lastNameIs(String expectedName) {
        assertThat(registerUserResponse.getLastName())
                .withFailMessage(String.format("lastName should be %s but was %s",
                        expectedName,
                        registerUserResponse.getLastName()))
                .isEqualTo(expectedName);
        return this;
    }

    public UserRegistrationAsserts addressIs(String expectedAddress) {
        assertThat(registerUserResponse.getAddress())
                .withFailMessage(String.format("address should be %s but was %s",
                        expectedAddress,
                        registerUserResponse.getAddress()))
                .isEqualTo(expectedAddress);
        return this;
    }

    public UserRegistrationAsserts cityIs(String expectedCity) {
        assertThat(registerUserResponse.getCity())
                .withFailMessage(String.format("city should be %s but was %s",
                        expectedCity,
                        registerUserResponse.getCity()))
                .isEqualTo(expectedCity);
        return this;
    }

    public UserRegistrationAsserts countryIs(String expectedCountry) {
        assertThat(registerUserResponse.getCountry())
                .withFailMessage(String.format("country should be %s but was %s",
                        expectedCountry,
                        registerUserResponse.getCountry()))
                .isEqualTo(expectedCountry);
        return this;
    }

    public UserRegistrationAsserts phoneIs(String expectedPhone) {
        assertThat(registerUserResponse.getPhone())
                .withFailMessage(String.format("phone should be %s but was %s",
                        expectedPhone,
                        registerUserResponse.getPhone()))
                .isEqualTo(expectedPhone);
        return this;
    }

    public UserRegistrationAsserts createdAtIsNotNull() {
        assertThat(registerUserResponse.getCreatedAt())
                .withFailMessage("createdAt should not be null")
                .isNotNull();
        return this;
    }
}