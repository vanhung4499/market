package com.hnv99.market.image.exception;

import com.hnv99.market.common.exception.BaseException;

import static com.hnv99.market.common.exception.ErrorCode.IMAGE_CONVERT_FAIL;

public class ImageConvertFailException extends BaseException {
    public ImageConvertFailException(Throwable cause) {
        super(IMAGE_CONVERT_FAIL, cause);
    }
}
