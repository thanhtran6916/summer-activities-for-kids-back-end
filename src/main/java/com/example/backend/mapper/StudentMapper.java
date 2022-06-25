package com.example.backend.mapper;

import com.example.backend.dto.StudentDTO;
import com.example.backend.entity.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    Student toStudent(StudentDTO studentDTO);

    StudentDTO toStudentDTO(Student student);

    List<StudentDTO> toListStudentDTO(List<Student> students);
}
