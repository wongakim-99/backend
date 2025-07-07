package org.project.ttokttok.domain.club.service.dto.response;

import java.util.List;

/**
 * 동아리 리스트 조회 서비스 응답 DTO
 * 서비스 레이어에서 동아리 목록 조회 결과를 담는 응답 DTO입니다.
 */
public record ClubListServiceResponse(
        List<ClubCardServiceResponse> clubs,  // 동아리 카드 리스트
        int currentPage,                      // 현재 페이지
        int totalPages,                       // 전체 페이지 수
        long totalElements,                   // 전체 동아리 수
        int size,                            // 페이지 크기
        boolean first,                       // 첫 페이지 여부
        boolean last,                        // 마지막 페이지 여부
        boolean hasNext,                     // 다음 페이지 존재 여부
        boolean hasPrevious                  // 이전 페이지 존재 여부
) {
} 