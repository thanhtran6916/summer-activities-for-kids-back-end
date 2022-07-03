package com.example.backend.api;

import com.example.backend.dto.StudentDTO;
import com.example.backend.message.BaseResponse;
import com.example.backend.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("excel")
public class ExcelController {

    private final ExcelService excelService;

    @PostMapping("/upload")
    public ResponseEntity<BaseResponse> uploadFile(@RequestParam("file")MultipartFile file) {
        BaseResponse baseResponse = new BaseResponse();
        String fileExcel = excelService.genStudentsPass(file);
        baseResponse.setData(fileExcel);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
