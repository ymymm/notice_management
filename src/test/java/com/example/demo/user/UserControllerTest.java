package com.example.demo.user;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.demo.user.request.UserSignInRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 회원가입() throws Exception {
        String request = objectMapper.writeValueAsString(
                new UserSignInRequest("마바사", "1234"));

        mockMvc.perform(post("/user/signUp")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void 회원가입_실패() throws Exception {
        //중복된 이름일 경우
        String request = objectMapper.writeValueAsString(
                new UserSignInRequest("가나다", "1234"));

        mockMvc.perform(post("/user/signUp")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is5xxServerError());

    }

    @Test
    void 로그인() throws Exception {
        String request = objectMapper.writeValueAsString(
                new UserSignInRequest("가나다", "1234"));

        mockMvc.perform(post("/user/signIn")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }
}