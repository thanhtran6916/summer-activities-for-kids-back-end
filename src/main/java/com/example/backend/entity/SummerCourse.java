package com.example.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SummerCourse extends BaseEntity {

    @Id
    @GeneratedValue(generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "summer_cource_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "address")
    private String address; // Địa chỉ diễn ra khóa hè

    @Column(name = "START_TIME")
    private Date startTime; // Thời gian bắt đầu khóa hè

    @Column(name = "END_TIME")
    private Date endTime; // Thời gian kết thúc khóa hè

    @Column(name = "TEMPLE_NAME")
    private String templeName; // Tên Chùa

    @Column(name = "TOTAL_REGISTRATION")
    private Integer totalRegistration; // Tổng học sinh đăng ký

    @Column(name = "ELECT")
    private Integer elect; // Trúng tuyển

    @Column(name = "STATUS_LINK")
    private String statusLink; // Trạng thái link

    @Column(name = "STATUS")
    private String status; // Trạng thái khóa hè
}
