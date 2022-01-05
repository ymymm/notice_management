package com.example.demo.notice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class NoticeControllerTest {
    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    @WithMockUser(username = "mockTest")
    public void 등록_성공() throws Exception {
        MockMultipartFile firstFile = new MockMultipartFile("files", "testFile1.txt", "text/plain", "test file".getBytes());
        MockMultipartFile secondFile = new MockMultipartFile("files", "testFile2.txt", "text/plain", "test 2 file".getBytes());
        MockMultipartFile jsonFile = new MockMultipartFile("notice", "",
                "application/json",
                ("{\"subject\" : \"등록 테스트\",\"content\": \"반갑습니다\"" +
                        ",\"startDate\" : \"2022-01-01T00:00:00\", \"endDate\" : \"2022-01-10T00:00:00\"}").getBytes());

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(MockMvcRequestBuilders.multipart("/notice")
                .file(firstFile)
                .file(secondFile)
                .file(jsonFile))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "mockTest")
    public void 업데이트_성공() throws Exception{
        MockMultipartFile firstFile = new MockMultipartFile("files", "update file 1.txt", "text/plain", "test file".getBytes());
        MockMultipartFile secondFile = new MockMultipartFile("files", "update file 2.txt", "text/plain", "test 2 file".getBytes());
        MockMultipartFile jsonFile = new MockMultipartFile("notice", "",
                "application/json",
                ("{\"subject\" : \"수정테스트\",\"content\": \"안녕하세요~!\"" +
                        ",\"startDate\" : \"2022-01-01T00:00:00\", \"endDate\" : \"2022-01-10T00:00:00\"}").getBytes());

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        MockMultipartHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.multipart("/notice/2");

        builder.with(new RequestPostProcessor() {
            @Override
            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                request.setMethod("PUT");
                return request;
            }
        });

        mockMvc.perform(builder
                .file(firstFile)
                .file(secondFile)
                .file(jsonFile))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "mockTest")
    public void 조회_성공() throws Exception {
        String url = "/notice/1";
        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "mockTest")
    public void 조회_실패_1() throws Exception {
        //시작일 전일
        String url = "/notice/2";
        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().is5xxServerError());

    }

    @Test
    @WithMockUser(username = "mockTest")
    public void 조회_실패_2() throws Exception {
        //종료일자가 지났을 때
        String url = "/notice/3";
        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().is5xxServerError());

    }

    @Test
    @WithMockUser(username = "mockTest")
    public void 조회_실패_3() throws Exception {
        //없는 게시글인 경우
        String url = "/notice/100";
        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().is5xxServerError());

    }

    @Test
    @WithMockUser(username = "mockTest")
    public void 삭제_성공() throws Exception {
        String url = "/notice/1";
        mockMvc.perform(delete(url))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username = "mockTest")
    public void 삭제_실패() throws Exception {
        //컨텐츠가 없는 경우
        String url = "/notice/100";
        mockMvc.perform(delete(url))
                .andDo(print())
                .andExpect(status().is5xxServerError());

    }

}