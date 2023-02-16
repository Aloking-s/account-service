package com.mavericsystems.accountservice.exception;

public class CustomerIDNotFoundExistsException extends RuntimeException {

    public CustomerIDNotFoundExistsException(String message)
    {
        super(message);
    }


}