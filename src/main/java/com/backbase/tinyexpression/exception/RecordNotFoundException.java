package com.backbase.tinyexpression.exception;

/**
 * This exception is thrown when the record is missing in database.
 */
public class RecordNotFoundException extends RuntimeException {

    /**
     * Constructs a new runtime exception.
     */
    public RecordNotFoundException() {
        super("URL not found in DB");
    }
}
