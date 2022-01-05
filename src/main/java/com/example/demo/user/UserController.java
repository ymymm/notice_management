package com.example.demo.user;


import com.example.demo.auth.JwtTokenProvider;
import com.example.demo.common.ApiCommonCode;
import com.example.demo.exception.UserException;
import com.example.demo.user.entity.User;
import com.example.demo.user.request.UserFormRequest;
import com.example.demo.user.request.UserSignInRequest;
import com.example.demo.user.response.UserResponse;
import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(
            @RequestBody @Valid UserFormRequest userFormRequest
    ){
        userService.save(userFormRequest.toEntity());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signIn")
    public ResponseEntity<UserResponse> signIn(
            @RequestBody @Valid UserSignInRequest userSignInRequest
    ){
        User user = userService.findByName(userSignInRequest.getName());
        if (!passwordEncoder.matches(userSignInRequest.getPassword(), user.getPassword()))
            throw new UserException(ApiCommonCode.PASSWORD_ERROR);

        return ResponseEntity.ok(UserResponse.builder().userId(user.getId())
                .accessToken(jwtTokenProvider.createAccessToken(user.getId())).build());
    }
}
