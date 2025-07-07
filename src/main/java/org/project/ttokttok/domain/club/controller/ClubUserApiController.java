package org.project.ttokttok.domain.club.controller;

import lombok.RequiredArgsConstructor;
import org.project.ttokttok.domain.club.controller.dto.response.ClubDetailResponse;
import org.project.ttokttok.domain.club.controller.dto.response.ClubListResponse;
import org.project.ttokttok.domain.club.domain.enums.ClubCategory;
import org.project.ttokttok.domain.club.domain.enums.ClubType;
import org.project.ttokttok.domain.club.service.ClubUserService;
import org.project.ttokttok.global.annotation.auth.AuthUserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 동아리 관련 API 컨트롤러
 * 사용자가 동아리 정보를 조회할 수 있는 API들을 제공합니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clubs")
public class ClubUserApiController {

    private final ClubUserService clubService;

    /**
     * 동아리 상세 정보 조회 API
     * 특정 동아리의 상세 정보를 조회합니다.
     * 
     * @param username 인증된 사용자 이메일
     * @param clubId 조회할 동아리 ID
     * @return 동아리 상세 정보 (소개, 지원 정보, 멤버 수 등)
     */
    @GetMapping("/{clubId}/content")
    public ResponseEntity<ClubDetailResponse> getClubIntroduction(@AuthUserInfo String username,
                                                                  @PathVariable String clubId) {
        ClubDetailResponse response = ClubDetailResponse.from(
                clubService.getClubIntroduction(username, clubId)
        );

        return ResponseEntity.ok()
                .body(response);
    }

    /**
     * 동아리 목록 조회 API
     * 메인 화면에서 동아리 목록을 필터링하여 페이징 조회합니다.
     * 
     * @param category 동아리 카테고리 필터 (봉사, 예술, 문화 등) - 선택사항
     * @param type 동아리 분류 필터 (중앙, 연합, 학과) - 선택사항
     * @param recruiting 모집 여부 필터 (true: 모집중, false: 모집마감) - 선택사항
     * @param page 페이지 번호 (기본값: 0)
     * @param size 페이지 크기 (기본값: 20)
     * @param sort 정렬 방식 (latest: 최신순, popular: 인기순) - 기본값: latest
     * @return 필터링된 동아리 목록과 페이징 정보
     */
    @GetMapping
    public ResponseEntity<ClubListResponse> getClubList(
            @RequestParam(required = false) ClubCategory category,
            @RequestParam(required = false) ClubType type,
            @RequestParam(required = false) Boolean recruiting,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "latest") String sort) {

        ClubListResponse response = ClubListResponse.from(
                clubService.getClubList(category, type, recruiting, page, size, sort)
        );

        return ResponseEntity.ok(response);
    }
}
