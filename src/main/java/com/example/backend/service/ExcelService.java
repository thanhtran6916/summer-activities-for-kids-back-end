package com.example.backend.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;


public interface ExcelService {

    String genStudentsPass(MultipartFile fileExcel);

    String zipFile();
}
