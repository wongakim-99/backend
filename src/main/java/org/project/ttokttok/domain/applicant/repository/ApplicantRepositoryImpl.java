package org.project.ttokttok.domain.applicant.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
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
        return getApplicantPageQueryResponse(
                sortCriteria,
                evaluating,
                cursor,
                size,
                applyFormId,
                null);
    }

    @Override
    public ApplicantPageQueryResponse searchApplicantsByKeyword(String searchKeyword,
                                                                String sortCriteria,
                                                                boolean evaluating,
                                                                int cursor,
                                                                int size,
                                                                String applyFormId) {
        // 검색 키워드와 조건에 맞는 지원자 조회
        return getApplicantPageQueryResponse(
                sortCriteria,
                evaluating,
                cursor,
                size,
                applyFormId,
                searchKeyword);
    }

    // 공통 로직을 추출한 메소드
    private ApplicantPageQueryResponse getApplicantPageQueryResponse(String sortCriteria,
                                                                     boolean evaluating,
                                                                     int cursor,
                                                                     int size,
                                                                     String applyFormId,
                                                                     String searchKeyword) {
        // 조건에 맞는 총 개수 조회
        Long count = queryFactory
                .select(applicant.count())
                .from(applicant)
                .where(
                        applicant.applyForm.id.eq(applyFormId),
                        containsName(searchKeyword),
                        isEvaluating(evaluating)
                )
                .fetchOne();

        //TODO: 추후 OFFSET 사용하지 않는 방식으로 개선 필요.
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
                        containsName(searchKeyword),
                        isEvaluating(evaluating)
                )
                .orderBy(getSortCriteria(sortCriteria))
                .limit(size)
                .offset((long) size * (cursor - 1))
                .fetch();

        // 현재 페이지 번호 계산
        int totalPage = (int) Math.ceil((double) count / size);

        return ApplicantPageQueryResponse.builder()
                .currentPage(cursor)
                .totalPage(totalPage)
                .totalCount(count.intValue())
                .applicants(applicants)
                .build();
    }

    // 검색 키워드가 있는 경우 이름에 포함되는지 조건 추가
    private BooleanExpression containsName(String searchKeyword) {
        return searchKeyword != null ? applicant.name.contains(searchKeyword) : null;
    }

    // 평가 중 여부 확인
    private BooleanExpression isEvaluating(boolean evaluating) {
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
