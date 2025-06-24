package org.project.ttokttok.global.jwt.exception;

import org.project.ttokttok.global.error.ErrorMessage;
import org.project.ttokttok.global.error.exception.CustomException;

public class AlreadyLogoutException extends CustomException {
    public AlreadyLogoutException() {
        super(ErrorMessage.ALREADY_LOGOUT);
    }
}
