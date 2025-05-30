package com.BNKBankApp.exception;

public class NoSuchCartFoundException extends RuntimeException {
    public NoSuchCartFoundException(String message) {
        super(message);
    }
}
