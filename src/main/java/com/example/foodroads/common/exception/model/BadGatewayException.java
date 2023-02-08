package com.example.foodroads.common.exception.model;

import com.example.foodroads.common.exception.ErrorCode;

public class BadGatewayException extends FoodRoadsBaseException {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E502_BAD_GATEWAY;

    public BadGatewayException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

}
