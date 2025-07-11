package org.project.ttokttok.domain.applicant.controller;

import lombok.RequiredArgsConstructor;
import org.project.ttokttok.domain.applicant.controller.dto.response.ApplicantPageResponse;
import org.project.ttokttok.domain.applicant.controller.enums.Sort;
import org.project.ttokttok.domain.applicant.repository.ApplicantRepository;
import org.project.ttokttok.domain.applicant.service.ApplicantAdminService;
import org.project.ttokttok.domain.applicant.service.dto.request.ApplicantPageServiceRequest;
import org.project.ttokttok.domain.applicant.service.dto.response.ApplicantPageServiceResponse;
import org.project.ttokttok.global.annotation.auth.AuthUserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/applies")
public class ApplicantAdminApiController {

    private final ApplicantAdminService applicantAdminService;
    private final ApplicantRepository applicantRepository;

    @GetMapping("/{applyFormId}")
    public ResponseEntity<ApplicantPageResponse> getApplicantsPage(@AuthUserInfo String username,
                                                  @RequestParam(name = "sort", required = false, defaultValue = "GRADE") Sort sort,
                                                  @RequestParam(required = false, defaultValue = "false") boolean isEvaluating,
                                                  @RequestParam(required = false, defaultValue = "1") int cursor,
                                                  @RequestParam(required = false, defaultValue = "7") int size,
                                                  @PathVariable String applyFormId) {

        ApplicantPageServiceRequest request = ApplicantPageServiceRequest.of(
                username,
                sort.name(),
                isEvaluating,
                cursor,
                size,
                applyFormId
        );

        ApplicantPageResponse response = ApplicantPageResponse.from(
                applicantAdminService.getApplicantPage(request)
        );

        return ResponseEntity.ok()
                .body(response);
    }

}
