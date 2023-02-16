package com.mavericsystems.accountservice.exception;

public class AccountIDNotfoundException extends RuntimeException {
    public AccountIDNotfoundException(String message)
    {
        super(message);
    }
}