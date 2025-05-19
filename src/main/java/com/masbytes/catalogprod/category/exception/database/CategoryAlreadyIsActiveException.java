package com.masbytes.catalogprod.category.exception.database;

/**
 * Exception thrown when trying to activate a category that is already active.
 * Useful for enforcing logical state transitions within category management.
 * 
 * Extends RuntimeException.
 */

public class CategoryAlreadyIsActiveException extends RuntimeException {

    public CategoryAlreadyIsActiveException(String message) {
        super(message);
    }

    public CategoryAlreadyIsActiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryAlreadyIsActiveException(Throwable cause) {
        super(cause);
    }

}
