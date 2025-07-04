package org.project.ttokttok.domain.club.service;

import lombok.RequiredArgsConstructor;
import org.project.ttokttok.domain.applyform.domain.ApplyForm;
import org.project.ttokttok.domain.applyform.repository.ApplyFormRepository;
import org.project.ttokttok.domain.club.exception.ClubNotFoundException;
import org.project.ttokttok.domain.club.repository.ClubRepository;
import org.project.ttokttok.domain.club.service.dto.response.ClubIntroductionServiceResponse;
import org.project.ttokttok.domain.favorite.repository.FavoriteRepository;
import org.project.ttokttok.infrastructure.s3.service.S3Service;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubService {

    // 소개글 조회
    private final ClubRepository clubRepository;

    private final S3Service s3Service;

    public ClubIntroductionServiceResponse getClubIntroduction(String clubId) {
        if (!clubRepository.existsById(clubId))
            throw new ClubNotFoundException();

        return null;
    }
}
