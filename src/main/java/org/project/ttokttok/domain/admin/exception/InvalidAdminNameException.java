package org.project.ttokttok.domain.admin.exception;

import org.project.ttokttok.global.error.ErrorMessage;
import org.project.ttokttok.global.error.exception.CustomException;

public class InvalidAdminNameException extends CustomException {
    public InvalidAdminNameException() {
        super(ErrorMessage.INVALID_ADMIN);
    }
}
