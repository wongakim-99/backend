package org.project.ttokttok.domain.applicant.controller;

import lombok.RequiredArgsConstructor;
import org.project.ttokttok.domain.applicant.controller.dto.response.ApplicantDetailResponse;
import org.project.ttokttok.domain.applicant.controller.dto.response.ApplicantPageResponse;
import org.project.ttokttok.domain.applicant.controller.enums.Sort;
import org.project.ttokttok.domain.applicant.repository.ApplicantRepository;
import org.project.ttokttok.domain.applicant.service.ApplicantAdminService;
import org.project.ttokttok.domain.applicant.service.dto.request.ApplicantPageServiceRequest;
import org.project.ttokttok.domain.applicant.service.dto.response.ApplicantDetailServiceResponse;
import org.project.ttokttok.global.annotation.auth.AuthUserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/applies")
public class ApplicantAdminApiController {

    private final ApplicantAdminService applicantAdminService;

    @GetMapping
    public ResponseEntity<ApplicantPageResponse> getApplicantsPage(@AuthUserInfo String username,
                                                                   @RequestParam(name = "sort", required = false, defaultValue = "GRADE") Sort sort,
                                                                   @RequestParam(required = false, defaultValue = "false") boolean isEvaluating,
                                                                   @RequestParam(required = false, defaultValue = "1") int cursor,
                                                                   @RequestParam(required = false, defaultValue = "7") int size) {

        ApplicantPageServiceRequest request = ApplicantPageServiceRequest.of(
                username,
                sort.name(),
                isEvaluating,
                cursor,
                size
        );

        ApplicantPageResponse response = ApplicantPageResponse.from(
                applicantAdminService.getApplicantPage(request)
        );

        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/{applicantId}")
    public ResponseEntity<ApplicantDetailResponse> getApplicantDetail(@AuthUserInfo String username,
                                                                      @PathVariable String applicantId) {

        ApplicantDetailServiceResponse applicantDetail = applicantAdminService.getApplicantDetail(username, applicantId);

        ApplicantDetailResponse response = ApplicantDetailResponse.from(applicantDetail);

        return ResponseEntity.ok()
                .body(response);
    }
}

