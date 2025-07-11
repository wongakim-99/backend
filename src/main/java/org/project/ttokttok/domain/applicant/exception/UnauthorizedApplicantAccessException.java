package org.project.ttokttok.domain.applicant.exception;

import org.project.ttokttok.global.exception.ErrorMessage;
import org.project.ttokttok.global.exception.exception.CustomException;

public class UnauthorizedApplicantAccessException extends CustomException {
    public UnauthorizedApplicantAccessException() {
        super(ErrorMessage.UNAUTHORIZED_APPLICANT_ACCESS);
    }
}