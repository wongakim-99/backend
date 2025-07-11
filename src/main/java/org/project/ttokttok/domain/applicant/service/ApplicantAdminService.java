package org.project.ttokttok.domain.applicant.service;

import lombok.RequiredArgsConstructor;
import org.project.ttokttok.domain.admin.repository.AdminRepository;
import org.project.ttokttok.domain.applicant.domain.dto.ApplicantPageDto;
import org.project.ttokttok.domain.applicant.repository.ApplicantRepository;
import org.project.ttokttok.domain.applicant.repository.dto.response.ApplicantPageQueryResponse;
import org.project.ttokttok.domain.applicant.service.dto.request.ApplicantPageServiceRequest;
import org.project.ttokttok.domain.applicant.service.dto.response.ApplicantPageServiceResponse;
import org.project.ttokttok.domain.applyform.domain.ApplyForm;
import org.project.ttokttok.domain.applyform.exception.ApplyFormNotFoundException;
import org.project.ttokttok.domain.applyform.repository.ApplyFormRepository;
import org.project.ttokttok.domain.club.domain.Club;
import org.project.ttokttok.domain.club.exception.NotClubAdminException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicantAdminService {

    private final ApplicantRepository applicantRepository;
    private final ApplyFormRepository applyFormRepository;
    private final AdminRepository adminRepository;

    public ApplicantPageServiceResponse getApplicantPage(ApplicantPageServiceRequest request) {
        // 1. username을 통해 동아리 관리자가 맞는지 검증
        ApplyForm applyForm = applyFormRepository.findById(request.applyFormId())
                .orElseThrow(ApplyFormNotFoundException::new);

        Club club = applyForm.getClub();

        validateAdmin(request.username(), club.getAdmin().getUsername());

        // 2. DTO 지원자 페이지를 조회
        return ApplicantPageServiceResponse.from(applicantRepository.findApplicantsPageWithSortCriteria(
                request.sortCriteria(),
                request.isEvaluating(),
                request.cursor(),
                request.size(),
                request.applyFormId()
        ).toDto());
    }

    private void validateAdmin(String adminName, String targetAdminName) {
        if (!adminName.equals(targetAdminName)) {
            throw new NotClubAdminException();
        }
    }
}
