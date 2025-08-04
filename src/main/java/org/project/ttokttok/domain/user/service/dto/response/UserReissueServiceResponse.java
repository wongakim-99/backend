package org.project.ttokttok.domain.user.service.dto.response;

import org.project.ttokttok.global.auth.jwt.dto.response.TokenResponse;

public record UserReissueServiceResponse(
        String accessToken,
        String refreshToken,
        Long ttl
) {
    public static UserReissueServiceResponse of(final TokenResponse tokens, final Long ttl) {
        return new UserReissueServiceResponse(
                tokens.accessToken(),
                tokens.refreshToken(),
                ttl
        );
    }
}
