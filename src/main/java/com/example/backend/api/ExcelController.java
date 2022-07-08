package com.example.backend.api;

import com.example.backend.message.BaseResponse;
import com.example.backend.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.IOUtils;

import org.aspectj.util.FileUtil;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@RestController
@RequiredArgsConstructor
@RequestMapping("excel")
@CrossOrigin
public class ExcelController {

    private final ExcelService excelService;

    @PostMapping("/upload")
    public ResponseEntity<BaseResponse> uploadFile(@RequestParam("file")MultipartFile file) {
        BaseResponse baseResponse = new BaseResponse();
        String fileExcel = excelService.genStudentsPass(file);
        baseResponse.setData(fileExcel);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/get-file")
    public @ResponseBody byte[] getFile() throws IOException{
        String filename = excelService.zipFile();
        File file = new File(filename);
        InputStream in = new FileInputStream(file);
        return IOUtils.toByteArray(in);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");

//        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(file.length())
//                .contentType(MediaType.MULTIPART_FORM_DATA)
//                .body(resource);

    }
    @GetMapping("/get-file2")
    public void pdfToriai(HttpServletResponse response) {
        try {
            String filename = excelService.zipFile();
            File file = new File(filename);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, StandardCharsets.UTF_8.name()));
            response.setContentType("application/pdf");
            ServletOutputStream servletOutputStream = response.getOutputStream();
            FileUtils.copyFile(file, servletOutputStream);
            servletOutputStream.close();
            servletOutputStream.flush();
        } catch (Exception e) {
//            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
