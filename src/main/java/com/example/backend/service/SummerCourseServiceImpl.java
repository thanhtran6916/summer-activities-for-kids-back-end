package com.example.backend.service;

import com.example.backend.dto.SummerCourseDTO;
import com.example.backend.entity.SummerCourse;
import com.example.backend.mapper.SummerCourseMapper;
import com.example.backend.message.BaseResponse;
import com.example.backend.repository.SummerCourseRepository;
import com.example.backend.util.Constant;
import com.example.backend.util.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SummerCourseServiceImpl implements SummerCourseService {

    private final SummerCourseRepository summerCourseRepository;
    private final SummerCourseMapper summerCourseMapper;

    @Override
    public BaseResponse findSummerCourseById(Integer id) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            Optional<SummerCourse> summerCourse = summerCourseRepository.findById(id);
            if (summerCourse.isPresent()) {
                baseResponse.setData(summerCourseMapper.toSummerCourseDTO(summerCourse.get()));
            } else {
                baseResponse.setErrorCode(Constant.ERROR_CODE);
                baseResponse.setMessage(MessageUtils.getMessage("summer.course.find.by.id.empty"));
            }
        } catch (Exception e) {
            baseResponse.setErrorCode(Constant.ERROR_CODE);
            baseResponse.setMessage(MessageUtils.getMessage("summer.course.find.by.id.error"));
            log.info(e.getMessage());
        }
        return baseResponse;
    }

    @Override
    public BaseResponse findAll() {
        BaseResponse baseResponse = new BaseResponse();
        try {
            List<SummerCourse> summerCourses = summerCourseRepository.findAll();
            if (!CollectionUtils.isEmpty(summerCourses)) {
                baseResponse.setData(summerCourseMapper.toListSummerCourseDTO(summerCourses));
            }
        } catch (Exception e) {
            baseResponse.setErrorCode(Constant.ERROR_CODE);
            baseResponse.setMessage(MessageUtils.getMessage("summer.course.find.all.error"));
            log.info(e.getMessage());
        }
        return baseResponse;
    }

    @Override
    public BaseResponse insertOrUpdateSummerCourse(SummerCourseDTO summerCourseDTO) {
        if (!ObjectUtils.isEmpty(summerCourseDTO.getId())) {
            return updateSummerCourse(summerCourseDTO);
        } else {
            return insertSummerCourse(summerCourseDTO);
        }
    }

    private BaseResponse insertSummerCourse(SummerCourseDTO summerCourseDTO) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            SummerCourse summerCourse = summerCourseMapper.toSummerCourse(summerCourseDTO);
            summerCourse = summerCourseRepository.save(summerCourse);
            if (!ObjectUtils.isEmpty(summerCourse)) {
                baseResponse.setErrorCode(Constant.ERROR_CODE);
                baseResponse.setMessage(MessageUtils.getMessage("summer.course.insert.error"));
            }
            baseResponse.setData(summerCourseMapper.toSummerCourseDTO(summerCourse));
        } catch (Exception e) {
            baseResponse.setErrorCode(Constant.ERROR_CODE);
            baseResponse.setMessage(MessageUtils.getMessage("summer.course.insert.error"));
            log.info(e.getMessage());
        }
        return baseResponse;
    }

    private BaseResponse updateSummerCourse(SummerCourseDTO summerCourseDTO) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            if (ObjectUtils.isEmpty(summerCourseDTO.getId())) {
                baseResponse.setErrorCode(Constant.ERROR_CODE);
                baseResponse.setMessage(MessageUtils.getMessage("summer.course.update.id.empty"));
            }
            Optional<SummerCourse> summerCourse = summerCourseRepository.findById(summerCourseDTO.getId());
            if (summerCourse.isPresent()) {
                SummerCourse summerCourseResult = summerCourseRepository
                        .save(summerCourseMapper.toSummerCourse(summerCourseDTO));
                if (ObjectUtils.isEmpty(summerCourseResult)) {
                    baseResponse.setErrorCode(Constant.ERROR_CODE);
                    baseResponse.setMessage(MessageUtils.getMessage("summer.course.update.error"));
                }
                baseResponse.setData(summerCourseMapper.toSummerCourseDTO(summerCourseResult));
            } else {
                baseResponse.setErrorCode(Constant.ERROR_CODE);
                baseResponse.setMessage(MessageUtils.getMessage("summer.course.update.empty"));
            }
        } catch (Exception e) {
            baseResponse.setErrorCode(Constant.ERROR_CODE);
            baseResponse.setMessage(MessageUtils.getMessage("summer.course.update.error"));
        }
        return baseResponse;
    }

}
