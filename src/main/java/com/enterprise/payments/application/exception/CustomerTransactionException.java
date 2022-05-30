package com.enterprise.payments.application.exception;

/**
 * Application Exception to be thrown in case of any problems.
 *
 * @author amit chandra
 */
public class CustomerTransactionException extends RuntimeException {

    public CustomerTransactionException(String message) {
        super(message);
    }


}
