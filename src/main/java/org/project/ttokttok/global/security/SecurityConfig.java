package org.project.ttokttok.global.security;

import lombok.RequiredArgsConstructor;
import org.project.ttokttok.global.jwt.filter.JwtAuthenticationManager;
import org.project.ttokttok.global.jwt.filter.TokenAuthenticationFilter;
import org.project.ttokttok.global.jwt.service.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.project.ttokttok.global.security.SecurityWhiteList.ALLOW_URLS;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationManager jwtAuthenticationManager;

    // h2 db 접근용 security 비활성화
    @Bean
    public WebSecurityCustomizer customizer() {
        return web -> web.ignoring()
                .requestMatchers(toH2Console());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider, jwtAuthenticationManager);
    }

    // todo: 추후에 보안 사항에 맞게 수정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(request ->
                        request.requestMatchers(ALLOW_URLS.getEndPoints()).permitAll()
                                .requestMatchers("/api/admin/*").hasRole("ADMIN")
                                .anyRequest().permitAll()
                )
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}