package org.project.ttokttok.global.jwt.exception;

import org.project.ttokttok.global.error.ErrorMessage;
import org.project.ttokttok.global.error.exception.CustomException;

public class InvalidTokenFromCookieException extends CustomException {
    public InvalidTokenFromCookieException() {
        super(ErrorMessage.INVALID_TOKEN_AT_COOKIE);
    }
}
