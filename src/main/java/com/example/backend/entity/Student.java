package com.example.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "STUDENT")
public class Student extends BaseEntity {

    @Id
    @GeneratedValue(generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "student_id_seq", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DHARMA_NAME")
    private String dharmaName; // pháp danh

    @Column(name = "REGISTER_FOR_REFUGE")
    private String registerForRefuge; // Đăng ký quy y

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "YEAR_BIRTH")
    private Integer yearBirth;

    @Column(name = "HEIGHT")
    private Float height;

    @Column(name = "WEIGHT")
    private Float weight;

    @Column(name = "RELATIVE_NAME")
    private String relativeName; // họ tên người thân

    @Column(name = "RELATIVE_PHONE")
    private String relativePhone; // số điện thoại người thân

    @Column(name = "APARTMENT_NUMBER")
    private String apartmentNumber; // số nhà

    @Column(name = "WARD")
    private String ward; // phường/xã

    @Column(name = "DISTRICT")
    private String district; // quận/huyện

    @Column(name = "PROVINCE")
    private String province; // tỉnh/thành phố

    @Column(name = "ADDRESS")
    private String address; // địa chỉ nơi ở hiện tại

    @Column(name = "NUMBER_OF_PARTICIPATION")
    private Integer numberOfParticipation; // số lần đã tham gia

    @Column(name = "HEALTH_CONDITION")
    private String healthCondition; // tình trạng sức khỏe

    @Column(name = "NOTE_HEALTH_CONDITION")
    private String noteHealthCondition; // ghi chú tình trạng sức khỏe

    @Column(name = "CONDUCT")
    private String conduct; // hạnh kiểm

    @Column(name = "ACADEMIC_ABILITY")
    private String academicAbility; // học lực

    @Column(name = "SUMMER_COURSE_ID")
    private String summerCourseId;

    @Column(name = "INTRODUCE_PERSON")
    private String introducePerson; // người giới thiệu

    @Column(name = "INTRODUCE_PERSON_PHONE")
    private String introducePersonPhone; // số điện thoại người giới thiệu

    @Column(name = "INTRODUCE_PERSON_IS_YOUTHS")
    private String introducePersonIsYouth; // người giới thiệu đang sinh hoạt ở trong chúng thanh niên

    @Column(name = "ACTIVE_GROUP_NAME")
    private String activeGroupName; // tên tổ người giới thiệu đang sinh hoạt

    @Column(name = "RESULT")
    private String result; // Đủ điều kiện hay không

    @Column(name = "STATUS")
    private String status; // Trạng thái

    @Column(name = "REASON_REJECT")
    private String reasonReject; // lý do từ chối
}
