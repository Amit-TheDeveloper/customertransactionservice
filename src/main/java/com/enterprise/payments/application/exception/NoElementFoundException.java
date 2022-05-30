package com.enterprise.payments.application.exception;

/**
 * Thrown in case No records are found.
 *
 * @author amit chandra
 */
public class NoElementFoundException extends RuntimeException {

    public NoElementFoundException(String message) {
        super(message);
    }


}
