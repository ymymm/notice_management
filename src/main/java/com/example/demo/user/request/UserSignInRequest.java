package com.example.demo.user.request;

import com.example.demo.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserSignInRequest {

    @NotNull
    private String name;

    @NotNull
    private String password;


    public User toEntity() {
        return User.builder()
                .password(this.password)
                .name(this.name).build();
    }

    @Builder
    public UserSignInRequest(String name, String password){
        this.name = name;
        this.password = password;

    }
}
