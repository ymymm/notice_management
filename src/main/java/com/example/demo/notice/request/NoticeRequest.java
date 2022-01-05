package com.example.demo.notice.request;

import com.example.demo.notice.entity.Notice;
import com.example.demo.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class NoticeRequest {
    @NotNull
    private String subject;
    @NotNull
    private String content;
    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime endDate;

    @Builder
    public NoticeRequest(String subject, String content, LocalDateTime startDate, LocalDateTime endDate){
        this.subject = subject;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Notice toEntity(User user) {
        return Notice.builder()
                .subject(this.subject)
                .content(this.content)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .createDate(LocalDateTime.now())
                .user(user)
                .views(0).build();
    }

    public Notice toEntity(Notice notice) {
        return notice.builder()
                .subject(this.subject)
                .content(this.content)
                .startDate(this.startDate)
                .endDate(this.endDate).build();
    }
}
