package com.mavericsystems.accountservice.exception;

public class PathParamsVsInputParamsMismatchException extends RuntimeException{
    public PathParamsVsInputParamsMismatchException(String message) {
        super(message);
    }
}
