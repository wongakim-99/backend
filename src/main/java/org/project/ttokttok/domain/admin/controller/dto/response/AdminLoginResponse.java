package org.project.ttokttok.domain.admin.controller.dto.response;

import lombok.Builder;
import org.project.ttokttok.domain.admin.service.dto.response.AdminLoginServiceResponse;

@Builder
public record AdminLoginResponse(
        String accessToken,
        String refreshToken
) {
    public static AdminLoginResponse from(AdminLoginServiceResponse response) {
        return AdminLoginResponse.builder()
                .accessToken(response.accessToken())
                .refreshToken(response.refreshToken())
                .build();
    }
}
