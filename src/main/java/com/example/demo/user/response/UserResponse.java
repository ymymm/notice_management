package com.example.demo.user.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponse {
    private Long userId;
    private String accessToken;

    @Builder
    public UserResponse(Long userId, String accessToken){
        this.userId = userId;
        this.accessToken = accessToken;
    }
}
