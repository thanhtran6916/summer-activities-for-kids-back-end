package com.example.backend.api;

import com.example.backend.dto.StudentDTO;
import com.example.backend.message.BaseResponse;
import com.example.backend.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
@Slf4j
@CrossOrigin
public class StudentController {

    private final StudentService studentService;

    @GetMapping("get/{id}")
    public ResponseEntity<BaseResponse> getStudent(@PathVariable Integer id) {
        BaseResponse result = studentService.findStudentById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<BaseResponse> getAll() {
        BaseResponse result = studentService.findAllStudent();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<BaseResponse> insertStudent(@RequestBody StudentDTO studentDTO) {
        BaseResponse result = studentService.insertStudent(studentDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<BaseResponse> updateStudent(@RequestBody StudentDTO studentDTO) {
        BaseResponse result = studentService.updateStudent(studentDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
