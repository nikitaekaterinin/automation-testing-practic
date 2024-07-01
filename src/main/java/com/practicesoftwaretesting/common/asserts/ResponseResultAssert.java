package com.practicesoftwaretesting.common.asserts;

import com.practicesoftwaretesting.common.responses.ResponseResult;
import lombok.AllArgsConstructor;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AllArgsConstructor
public class ResponseResultAssert {

    private ResponseResult responseResult;

    public ResponseResultAssert responseResultIs(String resultMessage) {
        assertThat(responseResult.getResult())
                .withFailMessage("Result is not as expected.")
                .isEqualTo(resultMessage);
        return this;
    }

    public ResponseResultAssert responseResultIsNotNull() {
        assertThat(responseResult.getResult())
                .withFailMessage("Result is not found.")
                .isNotNull();
        return this;
    }
}