package com.example.demo.notice.service;

import com.example.demo.common.ApiCommonCode;
import com.example.demo.exception.NoticeException;
import com.example.demo.notice.entity.Notice;
import com.example.demo.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public Notice save(Notice notice) {
        return noticeRepository.save(notice);
    }

    public Notice get(Long id) {
        return noticeRepository.findById(id).orElseGet(
                () -> { throw new NoticeException(ApiCommonCode.NOT_EXIST_NOTICE); });
    }

    public void delete(Long id) {
        noticeRepository.delete(
                this.get(id)
        );
    }

    public void updateViews(Notice notice){
        notice.updateViews();
        this.save(notice);
    }

    public void validityDate(Notice notice) {
        if(LocalDateTime.now().isBefore(notice.getStartDate())){
            throw new NoticeException(ApiCommonCode.BEFORE_DATE);
        } else if (LocalDateTime.now().isAfter(notice.getEndDate())){
            throw new NoticeException(ApiCommonCode.AFTER_DATE);
        }
    }
}
