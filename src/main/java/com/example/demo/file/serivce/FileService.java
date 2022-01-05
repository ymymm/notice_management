package com.example.demo.file.serivce;

import com.example.demo.common.ApiCommonCode;
import com.example.demo.exception.FileException;
import com.example.demo.file.entity.File;
import com.example.demo.file.repository.FileRepository;
import com.example.demo.notice.entity.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FileService {

    private final FileRepository fileRepository;

    @Value("${upload.folder.dir}")
    private String uploadPath;

    public void create(MultipartFile[] files, Notice notice){
        Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file, notice))
                .collect(Collectors.toList());

    }

    private File uploadFile(MultipartFile file, Notice notice) {
        try {
            Path path = Paths.get(uploadPath + file.getOriginalFilename());
            Files.write(path, file.getBytes());
        } catch (Exception e){
            throw new FileException(ApiCommonCode.FILE_UPLOAD_ERROR);
        }

        return fileRepository.save(File.builder()
                .notice(notice).path(uploadPath)
                .name(file.getOriginalFilename()).build());
    }

    public void delete(List<File> files) {
        files.forEach(file -> fileRepository.delete(file));
    }
}
