package com.example.foodroads.common.exception.model;

import com.example.foodroads.common.exception.ErrorCode;

public class UnAuthorizedException extends FoodRoadsBaseException {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E401_UNAUTHORIZED;

    public UnAuthorizedException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

}
