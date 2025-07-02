package org.project.ttokttok.domain.club.service;

import lombok.RequiredArgsConstructor;
import org.project.ttokttok.domain.club.domain.Club;
import org.project.ttokttok.domain.club.exception.ClubNotFoundException;
import org.project.ttokttok.domain.club.exception.NotClubAdminException;
import org.project.ttokttok.domain.club.repository.ClubRepository;
import org.project.ttokttok.domain.club.service.dto.request.ClubContentUpdateServiceRequest;
import org.project.ttokttok.domain.club.service.mapper.ClubMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClubAdminService {

    private final ClubRepository clubRepository;
    private final ClubMapper mapper;

    // 동아리 소개글 수정 로직
    // 더티 체킹을 통해 수정
    @Transactional
    public void updateContent(String username, ClubContentUpdateServiceRequest request) {
        Club club = clubRepository.findById(request.clubId())
                .orElseThrow(ClubNotFoundException::new);

        // 요청한 이가 이 동아리의 관리자가 맞는지 검증
        validateAdmin(username, club.getAdmin().getUsername());

        if (request.profileImage() != null && request.profileImage().isPresent()) {
            // s3에 저장하고 url를 받아와서 저장.

        }

        // 요청에서 null 값만 확인 후, mapper를 통해 업데이트
        mapper.updateClubFromRequest(club, request);

    }

    private void validateAdmin(String username, String targetAdminUsername) {
        if (!username.equals(targetAdminUsername))
            throw new NotClubAdminException();
    }
}
