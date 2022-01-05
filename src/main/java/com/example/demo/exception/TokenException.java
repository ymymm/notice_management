package com.example.demo.exception;

import com.example.demo.common.ApiCommonCode;
import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class TokenException extends RuntimeException {
    private ApiCommonCode apiCommonCode;

    public TokenException(ApiCommonCode apiCommonCode) {
        super(apiCommonCode.getMessage());
        this.apiCommonCode = apiCommonCode;
    }
}