package com.example.backend.service;

import org.springframework.web.multipart.MultipartFile;


public interface ExcelService {

    String genStudentsPass(MultipartFile fileExcel);
}
