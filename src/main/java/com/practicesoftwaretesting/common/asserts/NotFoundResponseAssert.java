package com.practicesoftwaretesting.common.asserts;

import com.practicesoftwaretesting.common.responses.ResponseMessage;

import lombok.AllArgsConstructor;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AllArgsConstructor
public class NotFoundResponseAssert {

    private ResponseMessage messageAfterDelete;

    public NotFoundResponseAssert userNotFound(String errorMessage) {
        assertThat(messageAfterDelete.getMessage())
                .withFailMessage("Error message doesn't match.")
                .isEqualTo(errorMessage);
        return this;
    }
}