package org.project.ttokttok.domain.applicant.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.project.ttokttok.domain.applicant.controller.enums.Kind;
import org.project.ttokttok.domain.applicant.domain.enums.Gender;
import org.project.ttokttok.domain.applicant.domain.enums.Grade;
import org.project.ttokttok.domain.applicant.domain.enums.Status;
import org.project.ttokttok.domain.applicant.domain.enums.StudentStatus;
import org.project.ttokttok.domain.applyform.domain.ApplyForm;
import org.project.ttokttok.global.entity.BaseTimeEntity;

@Entity
@Getter
@Table(name = "applicants")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "applicant_type", discriminatorType = DiscriminatorType.STRING)
public abstract class BaseApplicant extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36, updatable = false, unique = true)
    private String id;

    // 지원한 사용자의 인증 정보 통해서 받은 이메일
    @Column(nullable = false)
    private String userEmail;

    // 공통 필드들
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String major;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudentStatus studentStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Grade grade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applyform_id", nullable = false)
    private ApplyForm applyForm;

    // 공통 생성자와 메서드들
    protected BaseApplicant(String userEmail, String name, Integer age, String major,
                            String email, String phone, StudentStatus studentStatus,
                            Grade grade, Gender gender, ApplyForm applyForm) {
        this.userEmail = userEmail;
        this.name = name;
        this.age = age;
        this.major = major;
        this.email = email;
        this.phone = phone;
        this.studentStatus = studentStatus;
        this.grade = grade;
        this.gender = gender;
        this.status = Status.EVALUATING;
        this.applyForm = applyForm;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

    public abstract Kind getKind();
}