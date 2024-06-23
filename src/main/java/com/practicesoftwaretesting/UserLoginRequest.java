package com.practicesoftwaretesting;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginRequest {

    private String email;
    private String password;
}