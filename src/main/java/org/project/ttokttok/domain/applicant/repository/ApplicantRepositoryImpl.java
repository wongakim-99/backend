package org.project.ttokttok.domain.applicant.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.project.ttokttok.domain.applicant.domain.Applicant;
import org.project.ttokttok.domain.applicant.domain.QApplicant;
import org.project.ttokttok.domain.applicant.domain.dto.ApplicantPageDto;
import org.project.ttokttok.domain.applicant.domain.dto.ApplicantSimpleInfoDto;
import org.project.ttokttok.domain.applicant.repository.dto.response.ApplicantPageQueryResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.project.ttokttok.domain.applicant.domain.QApplicant.applicant;
import static org.project.ttokttok.domain.applicant.domain.enums.Status.EVALUATING;

@Repository
@RequiredArgsConstructor
public class ApplicantRepositoryImpl implements ApplicantCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public ApplicantPageQueryResponse findApplicantsPageWithSortCriteria(String sortCriteria,
                                                                         boolean evaluating,
                                                                         int cursor,
                                                                         int size,
                                                                         String applyFormId) {
        // evaluating이 true인 경우, 평가 중인 지원자만 조회
        Long count = queryFactory
                .select(applicant.count())
                .from(applicant)
                .where(applicant.applyForm.id.eq(applyFormId),
                        isEvaluating(evaluating))
                .fetchOne();

        List<ApplicantSimpleInfoDto> applicants = queryFactory
                .select(Projections.constructor(
                        ApplicantSimpleInfoDto.class,
                        applicant.id,
                        applicant.grade,
                        applicant.name,
                        applicant.major,
                        applicant.status
                ))
                .from(applicant)
                .where(
                        applicant.applyForm.id.eq(applyFormId),
                        isEvaluating(evaluating)
                )
                .orderBy(getSortCriteria(sortCriteria))
                .limit(size)
                .offset((long) size * (cursor - 1))
                .fetch();

        return ApplicantPageQueryResponse.builder()
                .currentPage(cursor + 1)
                .totalPage((int) Math.ceil((double) count / size))
                .totalCount(count.intValue())
                .applicants(applicants)
                .build();
    }

    // 평가 중 여부 확인
    private Predicate isEvaluating(boolean evaluating) {
        return evaluating ? applicant.status.eq(EVALUATING) : null;
    }

    // 들어온 sortCriteria에 따라 정렬 조건을 반환하는 메서드
    private OrderSpecifier<?> getSortCriteria(String sortCriteria) {
        switch (sortCriteria.toUpperCase()) {
            case "SUBMIT":
                return applicant.createdAt.asc();
            default:
                return applicant.grade.asc();
        }
    }
}
