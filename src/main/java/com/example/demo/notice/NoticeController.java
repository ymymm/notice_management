package com.example.demo.notice;

import com.example.demo.file.serivce.FileService;
import com.example.demo.notice.entity.Notice;
import com.example.demo.notice.request.NoticeRequest;
import com.example.demo.notice.response.NoticeResponse;
import com.example.demo.notice.service.NoticeService;
import com.example.demo.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
@Transactional
public class NoticeController {
    private final NoticeService noticeService;
    private final FileService fileService;

    @PostMapping("")
    public ResponseEntity<?> create(
            @RequestPart("notice") @Valid NoticeRequest noticeRequest,
            @RequestPart("files") MultipartFile[] files,
            @AuthenticationPrincipal User user
    ){
        Notice notice = noticeService.save(noticeRequest.toEntity(user));
        fileService.create(files, notice);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticeResponse> get(
            @PathVariable Long id
    ){
        Notice notice = noticeService.get(id);

        noticeService.validityDate(notice);
        noticeService.updateViews(notice);

        return ResponseEntity.ok(
                notice.toResponse());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestPart("notice") @Valid NoticeRequest noticeRequest,
            @RequestPart("files") MultipartFile[] files
    ){
        Notice notice = noticeService.get(id);
        fileService.delete(notice.getFile());
        Notice noticeResult = noticeRequest.toEntity(notice);

        noticeService.save(noticeResult);
        fileService.create(files, noticeResult);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ){
        noticeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
