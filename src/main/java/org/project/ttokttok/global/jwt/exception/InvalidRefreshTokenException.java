package org.project.ttokttok.global.jwt.exception;

import org.project.ttokttok.global.error.ErrorMessage;
import org.project.ttokttok.global.error.exception.CustomException;

public class InvalidRefreshTokenException extends CustomException {
    public InvalidRefreshTokenException() {
        super(ErrorMessage.INVALID_REFRESH_TOKEN);
    }
}
