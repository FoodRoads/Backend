package com.example.foodroads.common.exception.model;


import com.example.foodroads.common.exception.ErrorCode;

public class InternalServerException extends FoodRoadsBaseException {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E500_INTERNAL_SERVER;

    public InternalServerException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public InternalServerException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}
