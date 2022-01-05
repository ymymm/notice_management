package com.example.demo.notice.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class NoticeResponse {
    private String subject;
    private String content;
    private LocalDateTime createDate;
    private long views;
    private String writer;

    @Builder
    public NoticeResponse(String subject, String content, LocalDateTime createDate
            , long views, String writer){
        this.subject = subject;
        this.content = content;
        this.createDate = createDate;
        this.views = views;
        this.writer = writer;
    }

}
