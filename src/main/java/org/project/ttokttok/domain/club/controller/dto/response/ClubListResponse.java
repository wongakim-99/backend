package org.project.ttokttok.domain.club.controller.dto.response;

import org.project.ttokttok.domain.club.service.dto.response.ClubListServiceResponse;

import java.util.List;

/**
 * 동아리 리스트 조회 API 응답 DTO
 * 메인 화면에서 동아리 목록을 표시할 때 사용됩니다.
 * */
public record ClubListResponse(
        List<ClubCardResponse> clubs,        // 동아리 카드 리스트
        int currentPage,                     // 현재 페이지
        int totalPages,                      // 전체 페이지 수
        long totalElements,                  // 전체 동아리 수
        int size,                           // 페이지 크기
        boolean first,                      // 첫 페이지 여부
        boolean last,                       // 마지막 페이지 여부
        boolean hasNext,                    // 다음 페이지 존재 여부
        boolean hasPrevious                 // 이전 페이지 존재 여부
) {
    public static ClubListResponse from(ClubListServiceResponse response) {
        return new ClubListResponse(
                response.clubs().stream()
                        .map(ClubCardResponse::from)
                        .toList(),
                response.currentPage(),
                response.totalPages(),
                response.totalElements(),
                response.size(),
                response.first(),
                response.last(),
                response.hasNext(),
                response.hasPrevious()
        );
    }
} 