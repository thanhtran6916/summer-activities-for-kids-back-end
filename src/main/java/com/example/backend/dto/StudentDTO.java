package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {

    private Integer id;

    private String name;

    private String dharmaName; // pháp danh

    private String registerForRefuge; // Đăng ký quy y

    private String gender;

    private Integer yearBirth;

    private Float height;

    private Float weight;

    private String relativeName; // họ tên người thân

    private String relativePhone; // số điện thoại người thân

    private String appartmentNumber; // số nhà

    private String ward; // phường/xã

    private String district; // quận/huyện

    private String province; // tỉnh/thành phố

    private String address; // địa chỉ nơi ở hiện tại

    private Integer numberOfParticipation; // số lần đã tham gia

    private String healthCondition; // tình trạng sức khỏe

    private String noteHealthCondition; // ghi chú tình trạng sức khỏe

    private String conduct; // hạnh kiểm

    private String academicAability; // học lực

    private String summerCourseId;

    private String introducePerson; // người giới thiệu

    private String introducePersonPhone; // số điện thoại người giới thiệu

    private String introducePersonIsYouth; // người giới thiệu đang sinh hoạt ở trong chúng thanh niên

    private String activeGroupName; // tên tổ người giới thiệu đang sinh hoạt

    private String result; // Đủ điều kiện hay không

    private String status; // Trạng thái

    private String reasonReject; // lý do từ chối

    private Date createdDate;

    private String createdBy;
}
