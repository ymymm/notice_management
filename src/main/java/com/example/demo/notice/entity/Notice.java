package com.example.demo.notice.entity;

import com.example.demo.file.entity.File;
import com.example.demo.notice.response.NoticeResponse;
import com.example.demo.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String subject;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createDate;
    private long views;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "notice",
            fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private List<File> file = new ArrayList<>();

    @Builder
    public Notice(long id, String subject, String content, LocalDateTime startDate
            , LocalDateTime endDate, LocalDateTime createDate, long views, User user){
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createDate = createDate;
        this.views = views;
        this.user = user;
    }

    public NoticeResponse toResponse(){
        return NoticeResponse.builder()
                .subject(this.subject)
                .content(this.content)
                .createDate(this.createDate)
                .views(this.views)
                .writer(this.user.getName()).build();
    }

    public void updateViews(){
        this.views++;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
