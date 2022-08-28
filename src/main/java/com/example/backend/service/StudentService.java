package com.example.backend.service;

import com.example.backend.dto.StudentDTO;
import com.example.backend.message.BaseResponse;


public interface StudentService {

    BaseResponse findAllStudent();

    BaseResponse findStudentById(Integer id);

    BaseResponse createStudent(StudentDTO studentDTO);

    BaseResponse updateStudent(StudentDTO studentDTO);
}
