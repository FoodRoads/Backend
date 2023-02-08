package com.example.foodroads.common.exception.model;

import com.example.foodroads.common.exception.ErrorCode;

public class InvalidException extends FoodRoadsBaseException {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E400_INVALID;

    public InvalidException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public InvalidException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}
