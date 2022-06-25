package com.example.backend.service;

import com.example.backend.dto.StudentDTO;
import com.example.backend.entity.Student;
import com.example.backend.message.BaseResponse;

import java.util.List;

public interface StudentService {

    BaseResponse findAllStudent();

    BaseResponse findStudentById(Integer id);

    BaseResponse insertStudent(StudentDTO studentDTO);

    BaseResponse updateStudent(StudentDTO studentDTO);
}
