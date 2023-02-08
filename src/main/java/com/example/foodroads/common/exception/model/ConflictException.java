package com.example.foodroads.common.exception.model;

import com.example.foodroads.common.exception.ErrorCode;

public class ConflictException extends FoodRoadsBaseException {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E409_DUPLICATE;

    public ConflictException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public ConflictException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}
