package com.practicesoftwaretesting.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLoginResponse {

    private String accessToken;
    private String tokenType;
    private int expiresIn;
}