package com.example.backend.service;

import com.example.backend.dto.StudentDTO;
import com.example.backend.entity.Student;
import com.example.backend.exception.CustomException;
import com.example.backend.mapper.StudentMapper;
import com.example.backend.message.BaseResponse;
import com.example.backend.repository.StudentRepository;
import com.example.backend.util.Constant;
import com.example.backend.util.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;


    @Override
    public BaseResponse findAllStudent() {
        BaseResponse baseResponse = new BaseResponse();
        try {
            List<Student> students = studentRepository.findAll();
            baseResponse.setData(studentMapper.toListStudentDTO(students));
        } catch (Exception e) {
            baseResponse.setErrorCode(Constant.ERROR_CODE);
            baseResponse.setMessage(MessageUtils.getMessage("student.find.all.error"));
        }
        return baseResponse;
    }

    @Override
    public BaseResponse findStudentById(Integer id) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            Optional<Student> student = studentRepository.findById(id);
            if (student.isPresent()) {
                baseResponse.setData(student.get());
            } else {
                baseResponse.setMessage("student.find.by.id.empty");
            }
        } catch (Exception e) {
            baseResponse.setMessage(MessageUtils.getMessage("student.find.by.id.error", id));
            baseResponse.setErrorCode(Constant.ERROR_CODE);
            log.info(e.getMessage());
        }
        return baseResponse;
    }

    public BaseResponse insertStudent(StudentDTO studentDTO) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            studentDTO = studentMapper.toStudentDTO(
                    studentRepository.save(studentMapper.toStudent(studentDTO)));
            if (ObjectUtils.isEmpty(studentDTO)) {
                baseResponse.setErrorCode(Constant.ERROR_CODE);
                baseResponse.setMessage(MessageUtils.getMessage("student.insert.error"));
            }
            baseResponse.setData(studentDTO);
        } catch (Exception e) {
            baseResponse.setErrorCode(Constant.ERROR_CODE);
            baseResponse.setMessage(MessageUtils.getMessage("student.insert.error"));
            log.info(e.getMessage());
        }
        return baseResponse;
    }

    public BaseResponse updateStudent(StudentDTO studentDTO) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            if (ObjectUtils.isEmpty(studentDTO.getId())) {
                baseResponse.setErrorCode(Constant.ERROR_CODE);
                baseResponse.setMessage(MessageUtils.getMessage("student.update.id.null"));
                return baseResponse;
            }
            Optional<Student> student = studentRepository.findById(studentDTO.getId());
            if (student.isPresent()) {
                studentDTO = studentMapper.toStudentDTO(
                        studentRepository.save(studentMapper.toStudent(studentDTO)));
                if (ObjectUtils.isEmpty(studentDTO)) {
                    baseResponse.setErrorCode(Constant.ERROR_CODE);
                    baseResponse.setMessage(MessageUtils.getMessage("student.update.id.error", studentDTO.getId()));
                }
                baseResponse.setData(studentDTO);
            } else {
                baseResponse.setMessage(MessageUtils.getMessage("student.update.id.empty", studentDTO.getId()));
            }
        } catch (Exception e) {
            baseResponse.setErrorCode(Constant.ERROR_CODE);
            baseResponse.setMessage(MessageUtils.getMessage("student.update.id.error"));
            log.info(e.getMessage());
        }
        return baseResponse;
    }
}
