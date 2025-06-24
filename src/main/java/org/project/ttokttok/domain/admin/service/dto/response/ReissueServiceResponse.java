package org.project.ttokttok.domain.admin.service.dto.response;

import lombok.Builder;
import org.project.ttokttok.global.jwt.dto.response.TokenResponse;

@Builder
public record ReissueServiceResponse(
        String accessToken,
        String refreshToken
) {
    public static ReissueServiceResponse of(final TokenResponse tokens) {
        return ReissueServiceResponse.builder()
                .accessToken(tokens.accessToken())
                .refreshToken(tokens.refreshToken())
                .build();
    }
}
