package org.project.ttokttok.domain.applicant.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.project.ttokttok.domain.applicant.controller.enums.Kind;
import org.project.ttokttok.domain.applicant.domain.enums.Gender;
import org.project.ttokttok.domain.applicant.domain.enums.Grade;
import org.project.ttokttok.domain.applicant.domain.enums.StudentStatus;
import org.project.ttokttok.domain.applyform.domain.ApplyForm;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("INTERVIEW")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterviewApplicant extends BaseApplicant {

    // 연관된 서류 지원자 ID
    @Column(name = "document_applicant_id")
    private String documentApplicantId;

    // 면접 일시
    @Column(name = "interview_date_time", nullable = false)
    private LocalDate interviewDate;

    @Builder
    private InterviewApplicant(String userEmail, String name, Integer age, String major,
                               String email, String phone, StudentStatus studentStatus,
                               Grade grade, Gender gender, ApplyForm applyForm,
                               String documentApplicantId, LocalDate interviewDate) {

        super(userEmail, name, age, major, email, phone, studentStatus, grade, gender, applyForm);
        this.documentApplicantId = documentApplicantId;
        this.interviewDate = interviewDate; // 추후 null 체크 필요
    }

    @Override
    public Kind getKind() {
        return Kind.INTERVIEW;
    }

    public void updateInterviewDateTime(LocalDate newDateTime) {
        this.interviewDate = newDateTime;
    }
}
