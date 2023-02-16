package com.mavericsystems.accountservice.exception;

public class NoSuchCustomerExistsException extends RuntimeException {
    public NoSuchCustomerExistsException(String message)
    {
        super(message);
    }
}
