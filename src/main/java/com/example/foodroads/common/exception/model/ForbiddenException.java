package com.example.foodroads.common.exception.model;

import com.example.foodroads.common.exception.ErrorCode;

public class ForbiddenException extends FoodRoadsBaseException {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E403_FORBIDDEN;

    public ForbiddenException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public ForbiddenException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}
