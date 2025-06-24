package org.project.ttokttok.global.jwt.dto.response;

import lombok.Builder;

@Builder
public record UserProfileResponse(
        String username,
        String role
) {
}
