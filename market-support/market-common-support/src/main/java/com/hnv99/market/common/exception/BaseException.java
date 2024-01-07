package com.hnv99.market.common.exception;

public class BaseException extends RuntimeException {
    private static final String ID_PREFIX = " ID: ";

    private final ErrorCode errorCode;

    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

    private BaseException(ErrorCode errorCode, Long id) {
        super(errorCode.getMessage() + ID_PREFIX + id);
        this.errorCode = errorCode;
    }

    public static BaseException withId(ErrorCode errorCode, Long id) {
        return new BaseException(errorCode, id);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
