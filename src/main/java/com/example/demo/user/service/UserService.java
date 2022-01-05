package com.example.demo.user.service;

import com.example.demo.common.ApiCommonCode;
import com.example.demo.exception.UserException;
import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User findByName(String name) {
        return userRepository.findByName(name).orElseGet(
                () -> { throw new UserException(ApiCommonCode.NOT_EXIST_USER); });
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseGet(
                () -> { throw new UserException(ApiCommonCode.NOT_EXIST_USER); });
    }

    public void save(User user) {
        this.passwordEncode(user);
        userRepository.save(user);
    }

    public void passwordEncode(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

}
