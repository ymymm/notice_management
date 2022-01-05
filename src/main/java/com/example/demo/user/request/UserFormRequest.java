package com.example.demo.user.request;


import com.example.demo.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserFormRequest {
    private String name;
    private String password;
    private String phoneNumber;

    public User toEntity() {
        return User.builder()
                .password(this.password)
                .name(this.name)
                .phoneNumber(this.phoneNumber).build();
    }

    public User toEntity(Long id) {
        return User.builder()
                .id(id)
                .password(this.password)
                .name(this.name)
                .phoneNumber(this.phoneNumber).build();
    }

    @Builder
    public UserFormRequest(String name, String password, String phoneNumber){
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
