package org.project.ttokttok.global.annotationresolver;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.project.ttokttok.global.annotation.AuthUserInfo;
import org.project.ttokttok.global.jwt.TokenProperties;
import org.project.ttokttok.global.jwt.service.TokenProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static org.project.ttokttok.global.jwt.TokenProperties.AUTH_HEADER;
import static org.project.ttokttok.global.jwt.TokenProperties.BEARER_PREFIX;

@Configuration
@RequiredArgsConstructor
public class AuthUserInfoResolver implements HandlerMethodArgumentResolver {

    private final TokenProvider tokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthUserInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String token = request.getHeader(AUTH_HEADER.getValue());

        if (checkTokenForm(token)) {
            String parsedToken = token.substring(BEARER_PREFIX.getValue().length());

            // todo: 추후 리팩토링 고려하기
            return tokenProvider.getUsernameFromToken(parsedToken);
        }

        return null;
    }

    private boolean checkTokenForm(String token) {
        return token != null && token.startsWith(BEARER_PREFIX.getValue());
    }
}
