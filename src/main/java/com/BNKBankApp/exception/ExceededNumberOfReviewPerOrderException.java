package com.BNKBankApp.exception;

public class ExceededNumberOfReviewPerOrderException extends RuntimeException {
    public ExceededNumberOfReviewPerOrderException(String message) {
        super(message);
    }
}
