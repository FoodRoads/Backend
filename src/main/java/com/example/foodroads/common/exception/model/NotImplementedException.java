package com.example.foodroads.common.exception.model;

import com.example.foodroads.common.exception.ErrorCode;

public class NotImplementedException extends FoodRoadsBaseException {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E501_NOT_SUPPORTED;

    public NotImplementedException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public NotImplementedException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}
