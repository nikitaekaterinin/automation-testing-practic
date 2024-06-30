package com.practicesoftwaretesting.user.asserts;

import com.practicesoftwaretesting.user.model.UserLoginResponse;
import lombok.AllArgsConstructor;

import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
public class LoginUserAsserts {

    private UserLoginResponse userLoginResponse;

    public LoginUserAsserts accessTokenIsNotNull() {
        assertThat(userLoginResponse.getAccessToken())
                .withFailMessage("accessToken is nulll")
                .isNotNull();
        return this;
    }

    public LoginUserAsserts tokenTypeIs(String expectedTokenType) {
        assertThat(userLoginResponse.getTokenType())
                .withFailMessage(String.format(
                        "tokenType should be %s but was %s",
                        expectedTokenType,
                        userLoginResponse.getTokenType()
                ))
                .isEqualTo(expectedTokenType);
        return this;
    }

    public LoginUserAsserts isNotExpired() {
        assertThat(userLoginResponse.getExpiresIn())
                .withFailMessage("expiresIn should be greater than 0")
                .isGreaterThan(0);
        return this;
    }

}