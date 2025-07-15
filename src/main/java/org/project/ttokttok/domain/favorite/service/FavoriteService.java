package org.project.ttokttok.domain.favorite.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.ttokttok.domain.club.domain.Club;
import org.project.ttokttok.domain.club.exception.ClubNotFoundException;
import org.project.ttokttok.domain.club.repository.ClubRepository;
import org.project.ttokttok.domain.club.service.dto.response.ClubCardServiceResponse;
import org.project.ttokttok.domain.favorite.domain.Favorite;
import org.project.ttokttok.domain.favorite.repository.FavoriteRepository;
import org.project.ttokttok.domain.favorite.service.dto.request.FavoriteToggleServiceRequest;
import org.project.ttokttok.domain.favorite.service.dto.response.FavoriteListServiceResponse;
import org.project.ttokttok.domain.favorite.service.dto.response.FavoriteToggleServiceResponse;
import org.project.ttokttok.domain.user.domain.User;
import org.project.ttokttok.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 즐겨찾기 서비스 클래스
 * 즐겨찾기 추가/제거 및 조회 관련 비즈니스 로직을 처리합니다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final ClubRepository clubRepository;
    private final UserRepository userRepository;

    /**
     * 즐겨찾기 토글 (추가/제거)
     * 이미 즐겨찾기가 되어 있으면 제거하고, 없으면 추가합니다.
     *
     * @param request 즐겨찾기 토글 요청 정보
     * @return 즐겨찾기 토글 결과
     */
    @Transactional
    public FavoriteToggleServiceResponse toggleFavorite(FavoriteToggleServiceRequest request) {
        // 동아리 존재 확인
        Club club = clubRepository.findById(request.clubId())
                .orElseThrow(ClubNotFoundException::new);

        // 사용자 존재 확인
        User user = userRepository.findByEmail(request.userEmail())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 기존 즐겨찾기 확인
        Optional<Favorite> existingFavorite = favoriteRepository.findByUserEmailAndClubId(
                request.userEmail(), request.clubId());

        if (existingFavorite.isPresent()) {
            // 즐겨찾기 제거
            favoriteRepository.delete(existingFavorite.get());
            log.info("즐겨찾기 제거 완료: 사용자={}, 동아리={}", request.userEmail(), request.clubId());
            return FavoriteToggleServiceResponse.of(request.clubId(), false);
        } else {
            // 즐겨찾기 추가
            Favorite favorite = Favorite.builder()
                    .user(user)
                    .club(club)
                    .build();
            favoriteRepository.save(favorite);
            log.info("즐겨찾기 추가 완료: 사용자={}, 동아리={}", request.userEmail(), request.clubId());
            return FavoriteToggleServiceResponse.of(request.clubId(), true);
        }
    }

    /**
     * 사용자의 즐겨찾기 목록 조회
     *
     * @param userEmail 사용자 이메일
     * @return 즐겨찾기한 동아리 목록
     */
    @Transactional(readOnly = true)
    public FavoriteListServiceResponse getFavoriteList(String userEmail) {
        log.info("즐겨찾기 목록 조회 시작 : 사용자 = {}", userEmail);

        List<Favorite> favorites = favoriteRepository.findAllByUserEmailWithClub(userEmail);
        log.info("조회된 즐겨찾기 개수 : {}", favorites.size());

        // 각 즐겨찾기 정보 로그
        for (Favorite favorite : favorites) {
            log.info("즐겨찾기 정보 : 사용자 = {}, 동아리 = {}, 생성일 = {}",
                    favorite.getUser().getEmail(),
                    favorite.getClub().getId(),
                    favorite.getCreatedAt());
        }

        List<ClubCardServiceResponse> favoriteClubs = favorites.stream()
                .map(favorite -> toClubCardServiceResponse(favorite.getClub(), true))
                .toList();

        log.info("즐겨찾기 목록 조회 완료: 사용자={}, 개수={}", userEmail, favoriteClubs.size());
        return FavoriteListServiceResponse.of(favoriteClubs);
    }

    /**
     * 특정 동아리의 즐겨찾기 상태 확인
     *
     * @param userEmail 사용자 이메일
     * @param clubId 동아리 ID
     * @return 즐겨찾기 여부
     */
    @Transactional(readOnly = true)
    public boolean isFavorited(String userEmail, String clubId) {
        return favoriteRepository.existsByUserEmailAndClubId(userEmail, clubId);
    }

    /**
     * Club 엔티티를 ClubCardServiceResponse로 변환
     */
    private ClubCardServiceResponse toClubCardServiceResponse(Club club, boolean bookmarked) {
        return new ClubCardServiceResponse(
                club.getId(),
                club.getName(),
                club.getClubType(),
                club.getClubCategory(),
                club.getCustomCategory(),
                club.getSummary(),
                club.getProfileImageUrl(),
                club.getClubMembers().size(), // 멤버 수
                club.isRecruiting(),
                bookmarked
        );
    }
} 