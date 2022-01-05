package com.example.demo.exception;

import com.example.demo.common.ApiCommonCode;
import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class FileException extends RuntimeException {
    private ApiCommonCode apiCommonCode;

    public FileException(ApiCommonCode apiCommonCode) {
        super(apiCommonCode.getMessage());
        this.apiCommonCode = apiCommonCode;
    }
}