package org.project.ttokttok.domain.club.repository.dto;

import org.project.ttokttok.domain.club.domain.enums.ClubCategory;
import org.project.ttokttok.domain.club.domain.enums.ClubType;

import java.time.LocalDate;

public record ClubCardQueryResponse(
        String id,
        String name,
        ClubType clubType,
        ClubCategory clubCategory,
        String customCategory,
        String summary,
        String profileImageUrl,
        int clubMemberCount,
        boolean recruiting,
        boolean bookmarked,
        LocalDate applyDeadLine  // applyDeadLine -> applyEndDate로 변경
) {
}
