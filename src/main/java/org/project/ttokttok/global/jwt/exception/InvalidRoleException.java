package org.project.ttokttok.global.jwt.exception;

import org.project.ttokttok.global.error.ErrorMessage;
import org.project.ttokttok.global.error.exception.CustomException;

public class InvalidRoleException extends CustomException {
    public InvalidRoleException() {
        super(ErrorMessage.INVALID_ROLE);
    }
}
