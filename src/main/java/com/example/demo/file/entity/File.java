package com.example.demo.file.entity;

import com.example.demo.notice.entity.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String path;

    @ManyToOne
    @JoinColumn(name = "noticeId")
    private Notice notice;

    @Builder
    public File(Long id, String name, String path, Notice notice) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.notice = notice;
    }
}