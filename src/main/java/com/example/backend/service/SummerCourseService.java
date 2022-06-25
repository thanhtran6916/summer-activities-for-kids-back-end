package com.example.backend.service;

import com.example.backend.dto.SummerCourseDTO;
import com.example.backend.message.BaseResponse;

public interface SummerCourseService {

    BaseResponse findSummerCourseById(Integer id);

    BaseResponse findAll();

    BaseResponse insertOrUpdateSummerCourse(SummerCourseDTO summerCourseDTO);

}
