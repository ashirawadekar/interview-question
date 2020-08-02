package com.backbase.tinyexpression.exception;

/**
 * This exception is thrown when the tiny expression is not found.
 */
public class InvalidTinyExpressionException extends RuntimeException {

    /**
     * Constructs a new runtime exception.
     */
    public InvalidTinyExpressionException() {
        super("Not Found or Invalid Tiny Expression");
    }
}
