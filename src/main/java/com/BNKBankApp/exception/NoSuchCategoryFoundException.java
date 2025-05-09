package com.BNKBankApp.exception;

public class NoSuchCategoryFoundException extends RuntimeException {
    public NoSuchCategoryFoundException(String message) {
        super(message);
    }
}
