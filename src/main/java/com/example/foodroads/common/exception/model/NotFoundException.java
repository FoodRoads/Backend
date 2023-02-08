package com.example.foodroads.common.exception.model;

import com.example.foodroads.common.exception.ErrorCode;

public class NotFoundException extends FoodRoadsBaseException {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E404_NOT_EXISTS;

    public NotFoundException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public NotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}
