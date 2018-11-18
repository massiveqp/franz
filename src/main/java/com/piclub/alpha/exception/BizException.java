package com.piclub.alpha.exception;

import com.piclub.alpha.enums.ErrorMessage;

public class BizException extends RuntimeException {

    private ErrorMessage errorMessage;

    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
