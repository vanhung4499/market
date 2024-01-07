package com.hnv99.market.common.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public enum ErrorCode {
    // 400
    CANCELED_ORDER(BAD_REQUEST, "The order has already been canceled."),
    DELIVERED_ORDER(BAD_REQUEST, "The order has already been delivered."),
    DELETED_PRODUCT(BAD_REQUEST, "The product has been deleted."),
    NOT_CORRECT_QUANTITY(BAD_REQUEST, "The minimum quantity for the product is 1."),
    CLIENT_INPUT_INVALID(BAD_REQUEST, null),
    INORDERABLE_PRODUCT(BAD_REQUEST, "The product is not orderable."),
    INVALID_ORDER_LINE_INDEX(BAD_REQUEST, "Invalid value for the order product index."),

    // 401
    NOT_CORRECT_PAY_PASSWORD(UNAUTHORIZED, "Incorrect payment password."),
    NOT_CORRECT_JWT_SIGN(UNAUTHORIZED, "Incorrect JWT SIGN value."),
    NOT_CORRECT_JWT(UNAUTHORIZED, "Invalid JWT token."),
    EXPIRED_JWT_TOKEN(UNAUTHORIZED, "Expired token."),
    NOT_SUPPORTED_JWT_TOKEN(UNAUTHORIZED, "Unsupported token."),
    NOT_AUTHORIZED_TOKEN(UNAUTHORIZED, "Token without authorization information."),

    // 404
    NOT_FOUND_REVIEW(NOT_FOUND, "No existing reviews."),
    NOT_FOUND_REVIEW_LIKE(NOT_FOUND, "No existing review likes."),
    NOT_FOUND_ORDER_SUPPORT(NOT_FOUND, "No existing 1:1 inquiry history."),
    ORDER_NOT_FOUND(NOT_FOUND, "No existing order history."),
    NEVER_FAVORITE(NOT_FOUND, "No favorite history."),
    PRODUCT_NOT_FOUND(NOT_FOUND, "No existing product."),
    NEVER_WRITE_PRODUCT_SUPPORT(NOT_FOUND, "No written product inquiries."),
    NOT_EXISTS_USER(NOT_FOUND, "Nonexistent member."),
    PAYMENT_NOT_FOUND(NOT_FOUND, "No existing payment method."),
    CART_NOT_FOUND(NOT_FOUND, "No existing cart items."),
    ADDRESS_NOT_FOUND(NOT_FOUND, "Address not found."),
    CATEGORY_NOT_FOUND(NOT_FOUND, "Nonexistent category."),
    NEVER_LIKED(NOT_FOUND, "Never liked a review."),
    USER_LOGIN_FAILED(NOT_FOUND, "No existing user."),

    // 409
    NOT_ORDER_HOST(CONFLICT, "Not the user who placed the order."),
    NOT_AUTHOR(CONFLICT, "Not the author."),
    NOT_OWNER(CONFLICT, "Not the order owner."),
    ID_EXISTED(CONFLICT, "Unavailable ID."),
    EMAIL_EXISTED(CONFLICT, "Unavailable email."),

    // 422
    LOGIN_FAILED(UNPROCESSABLE_ENTITY, "Please check the ID and password."),

    // 500
    MARKET_SERVER_ERROR(INTERNAL_SERVER_ERROR, "Internal server problem. Please contact the administrator."),
    IMAGE_UPLOAD_FAIL(INTERNAL_SERVER_ERROR, "Failed to upload file."),
    IMAGE_CONVERT_FAIL(INTERNAL_SERVER_ERROR, "Failed to convert file."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
