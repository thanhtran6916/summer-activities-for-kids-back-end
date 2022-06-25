package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummerCourseDTO {

    private Integer id;

    private String address; // Địa chỉ diễn ra khóa hè

    private Date startTime; // Thời gian bắt đầu khóa hè

    private Date endTime; // Thời gian kết thúc khóa hè

    private String templeName; // Tên Chùa

    private Integer totalRegistration; // Tổng học sinh đăng ký

    private Integer elect; // Trúng tuyển

    private String statusLink; // Trạng thái link

    private String status; // Trạng thái khóa hè

    private Date createdDate;

    private String createdBy;
}
