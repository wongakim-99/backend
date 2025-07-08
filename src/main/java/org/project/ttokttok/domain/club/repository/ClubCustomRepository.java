package org.project.ttokttok.domain.club.repository;

import org.project.ttokttok.domain.club.domain.enums.ClubCategory;
import org.project.ttokttok.domain.club.domain.enums.ClubType;
import org.project.ttokttok.domain.club.repository.dto.ClubCardQueryResponse;
import org.project.ttokttok.domain.club.repository.dto.ClubDetailQueryResponse;

import java.util.List;

public interface ClubCustomRepository {
    // queryDsl 사용을 위한 인터페이스
    ClubDetailQueryResponse getClubIntroduction(String clubId, String email);

    // 무한스크롤을 위한 메서드
    List<ClubCardQueryResponse> getClubList(
            ClubCategory category,
            ClubType type,
            Boolean recruiting,
            int size,
            String cursor,
            String sort,
            String userEmail
    );

    // 멤버수 기준 인기 동아리 조회
    List<ClubCardQueryResponse> getPopularClubs(int offset, int limit, String userEmail);
}
