package org.project.ttokttok.domain.applicant.service;

import lombok.RequiredArgsConstructor;
import org.project.ttokttok.domain.admin.exception.AdminNotFoundException;
import org.project.ttokttok.domain.admin.repository.AdminRepository;
import org.project.ttokttok.domain.applicant.repository.ApplicantRepository;
import org.project.ttokttok.domain.applicant.service.dto.request.ApplicantPageServiceRequest;
import org.project.ttokttok.domain.applicant.service.dto.response.ApplicantPageServiceResponse;
import org.project.ttokttok.domain.applyform.domain.ApplyForm;
import org.project.ttokttok.domain.applyform.exception.ApplyFormNotFoundException;
import org.project.ttokttok.domain.applyform.repository.ApplyFormRepository;
import org.project.ttokttok.domain.club.domain.Club;
import org.project.ttokttok.domain.club.repository.ClubRepository;
import org.springframework.stereotype.Service;

import static org.project.ttokttok.domain.applyform.domain.enums.ApplyFormStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class ApplicantAdminService {

    private final ApplicantRepository applicantRepository;
    private final ApplyFormRepository applyFormRepository;
    private final AdminRepository adminRepository;
    private final ClubRepository clubRepository;

    public ApplicantPageServiceResponse getApplicantPage(ApplicantPageServiceRequest request) {
        // 1. username으로 관리하는 동아리 찾기
        // 존재하지 않으면 관리자 찾기 실패.
        Club club = clubRepository.findByAdminUsername(request.username())
                .orElseThrow(AdminNotFoundException::new);

        // 2. 현재 활성화된 지원 폼 찾기
        ApplyForm activeApplyForm = applyFormRepository.findByClubIdAndStatus(club.getId(), ACTIVE)
                .orElseThrow(ApplyFormNotFoundException::new);

        // 3. 활성화된 지원 폼의 ID를 사용해 지원자 페이지 조회
        return ApplicantPageServiceResponse.from(applicantRepository.findApplicantsPageWithSortCriteria(
                request.sortCriteria(),
                request.isEvaluating(),
                request.cursor(),
                request.size(),
                activeApplyForm.getId()
        ).toDto());
    }

//    private void validateAdmin(String adminName, String targetAdminName) {
//        if (!adminName.equals(targetAdminName)) {
//            throw new NotClubAdminException();
//        }
//    }
}
