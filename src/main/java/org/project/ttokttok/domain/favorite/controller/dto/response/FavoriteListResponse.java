package org.project.ttokttok.domain.favorite.controller.dto.response;

import org.project.ttokttok.domain.club.controller.dto.response.ClubCardResponse;
import org.project.ttokttok.domain.favorite.service.dto.response.FavoriteListServiceResponse;

import java.util.List;

/**
 * 즐겨찾기 목록 응답 DTO
 */
public record FavoriteListResponse(
        List<ClubCardResponse> favoriteClubs,
        int totalCount
) {
    public static FavoriteListResponse from(FavoriteListServiceResponse serviceResponse) {
        return new FavoriteListResponse(
                serviceResponse.favoriteClubs().stream()
                        .map(ClubCardResponse::from)
                        .toList(),
                serviceResponse.totalCount()
        );
    }
} 