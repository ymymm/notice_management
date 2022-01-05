package com.example.demo.exception;

import com.example.demo.common.ApiCommonCode;
import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class NoticeException extends RuntimeException {
    private ApiCommonCode apiCommonCode;

    public NoticeException(ApiCommonCode apiCommonCode) {
        super(apiCommonCode.getMessage());
        this.apiCommonCode = apiCommonCode;
    }
}