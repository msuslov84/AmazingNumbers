package com.suslov.jetbrains.exceptions;

/**
 * @author Mikhail Suslov
 */
public class NumException extends RuntimeException {

    public NumException(String message) {
        super(message);
    }

    public NumException(String message, Throwable cause) {
        super(message, cause);
    }
}
