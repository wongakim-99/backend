package org.project.ttokttok.global.jwt.exception;

import org.project.ttokttok.global.error.ErrorMessage;

public class RefreshTokenExpiredException extends RuntimeException {
    public RefreshTokenExpiredException() {
        super(ErrorMessage.REFRESH_TOKEN_EXPIRED);
    }
}
