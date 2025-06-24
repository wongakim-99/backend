package org.project.ttokttok.global.jwt.dto.request;

import lombok.Builder;
import org.project.ttokttok.global.entity.Role;

@Builder
public record TokenRequest(
        String id,
        String username,
        Role role
) {
    public static TokenRequest of(String id, String username, Role role) {
        return TokenRequest.builder()
                .id(id)
                .username(username)
                .role(role)
                .build();
    }
}
