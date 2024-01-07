package com.hnv99.market.web.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"success", "data"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    private final Boolean success;

    private final T data;

    BaseResponse(Boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public static BaseResponse<Void> ok(Boolean success) {
        return new BaseResponse<>(success, null);
    }

    public static <T> BaseResponse<T> ok(T data) {
        return new BaseResponse<>(true, data);
    }

    public static BaseResponse<Void> noData() {
        return new BaseResponse<>(true, null);
    }

    public Boolean getSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }
}
