package org.project.ttokttok.domain.applicant.repository;

import org.project.ttokttok.domain.applicant.repository.dto.response.ApplicantPageQueryResponse;

public interface ApplicantCustomRepository {
    ApplicantPageQueryResponse findApplicantsPageWithSortCriteria(String sortCriteria,
                                                                  boolean evaluating,
                                                                  int cursor,
                                                                  int size,
                                                                  String applyFormId);

    ApplicantPageQueryResponse searchApplicantsByKeyword(String searchKeyword,
                                                         String sortCriteria,
                                                         boolean evaluating,
                                                         int cursor,
                                                         int size,
                                                         String applyFormId);
}
