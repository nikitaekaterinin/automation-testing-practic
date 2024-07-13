package com.practicesoftwaretesting.common.asserts;

import com.practicesoftwaretesting.common.responses.ResponseMessage;

import lombok.AllArgsConstructor;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AllArgsConstructor
public class ResponseMessageAssert {

    private ResponseMessage message;

    public ResponseMessageAssert userNotFound(String errorMessage) {
        assertThat(message.getMessage())
                .withFailMessage("Error message doesn't match.")
                .isEqualTo(errorMessage);
        return this;
    }
}