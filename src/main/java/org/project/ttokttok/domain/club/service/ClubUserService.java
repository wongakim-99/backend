package org.project.ttokttok.domain.club.service;

import lombok.RequiredArgsConstructor;
import org.project.ttokttok.domain.club.domain.enums.ClubCategory;
import org.project.ttokttok.domain.club.domain.enums.ClubType;
import org.project.ttokttok.domain.club.exception.ClubNotFoundException;
import org.project.ttokttok.domain.club.repository.ClubRepository;
import org.project.ttokttok.domain.club.service.dto.response.ClubDetailServiceResponse;
import org.project.ttokttok.domain.club.service.dto.response.ClubListServiceResponse;
import org.project.ttokttok.infrastructure.s3.service.S3Service;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 동아리 서비스 클래스
 * 동아리 조회 관련 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
public class ClubUserService {

    // 소개글 조회
    private final ClubRepository clubRepository;

    private final Optional<S3Service> s3Service;

    /**
     * 동아리 상세 정보 조회
     * 특정 동아리의 상세 정보를 조회합니다.
     * 
     * @param username 사용자 이메일
     * @param clubId 동아리 ID
     * @return 동아리 상세 정보
     * @throws ClubNotFoundException 동아리를 찾을 수 없는 경우
     */
    public ClubDetailServiceResponse getClubIntroduction(String username, String clubId) {
        if (!clubRepository.existsById(clubId))
            throw new ClubNotFoundException();

        return ClubDetailServiceResponse.from(clubRepository.getClubIntroduction(clubId, username));
    }

    /**
     * 동아리 목록 조회
     * 필터링 조건에 따라 동아리 목록을 페이징 조회합니다.
     * 
     * @param category 동아리 카테고리 필터
     * @param type 동아리 분류 필터
     * @param recruiting 모집 여부 필터
     * @param page 페이지 번호
     * @param size 페이지 크기
     * @param sort 정렬 방식
     * @return 필터링된 동아리 목록과 페이징 정보
     */
    public ClubListServiceResponse getClubList(
            ClubCategory category,
            ClubType type, 
            Boolean recruiting,
            int page,
            int size,
            String sort) {
        
        // TODO: 실제 구현 필요 - 현재는 임시 응답
        // 1. 필터링 조건에 따른 동아리 목록 조회
        // 2. 페이징 처리
        // 3. 정렬 처리
        // 4. 각 동아리의 즐겨찾기 여부 확인
        // 5. 멤버 수 계산
        
        throw new UnsupportedOperationException("동아리 목록 조회 기능은 아직 구현되지 않았습니다.");
    }
}
