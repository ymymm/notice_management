package com.example.demo.file.repository;

import com.example.demo.file.entity.File;
import com.example.demo.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long>{
}
