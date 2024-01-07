package com.hnv99.market.image.exception;

import com.hnv99.market.common.exception.BaseException;

import static com.hnv99.market.common.exception.ErrorCode.IMAGE_UPLOAD_FAIL;

public class ImageUploadFailException extends BaseException {
    public ImageUploadFailException() {
        super(IMAGE_UPLOAD_FAIL);
    }
}
