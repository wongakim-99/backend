package org.project.ttokttok.global.jwt.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.ttokttok.global.jwt.dto.response.UserProfileResponse;
import org.project.ttokttok.global.jwt.service.TokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.project.ttokttok.global.jwt.TokenProperties.AUTH_HEADER;
import static org.project.ttokttok.global.jwt.TokenProperties.BEARER_PREFIX;

@Slf4j
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationManager jwtAuthManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        log.info("[JWT 인증 필터 실행]");

        final String AuthorizationHeader = request.getHeader(AUTH_HEADER.getValue());
        final String token = getAccessToken(AuthorizationHeader);

        if (tokenProvider.validateToken(token)) {
            UserProfileResponse profile = tokenProvider.getUserProfile(token);
            Authentication authentication = jwtAuthManager.getAuthentication(profile.username(), profile.role());

            log.info("[JWT 인증 완료] username(email)={}, role={}", profile.username(), profile.role());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getAccessToken(String header) {
        if (header != null && header.startsWith(BEARER_PREFIX.getValue())) {
            return header.substring(BEARER_PREFIX.getValue().length());
        }

        return null;
    }
}
