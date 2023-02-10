package com.example.foodroads.common.exception.model;

import com.example.foodroads.common.exception.ErrorCode;

public abstract class FoodRoadsBaseException extends RuntimeException {

    private final ErrorCode errorCode;

    public FoodRoadsBaseException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
