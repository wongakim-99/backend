package org.project.ttokttok.global.auth.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RootApiEndpoint {
    API_ADMIN("/api/admin"),
    API_USER("/api/user");

    final String value;
}
