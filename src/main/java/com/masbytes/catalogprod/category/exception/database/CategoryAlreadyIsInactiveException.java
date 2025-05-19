package com.masbytes.catalogprod.category.exception.database;

/**
 * Exception thrown when attempting to deactivate a category that is already
 * inactive.
 * Prevents redundant or logically incorrect deactivation requests.
 * 
 * Extends RuntimeException.
 */

public class CategoryAlreadyIsInactiveException extends RuntimeException {

    public CategoryAlreadyIsInactiveException(String message) {
        super(message);
    }

    public CategoryAlreadyIsInactiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryAlreadyIsInactiveException(Throwable cause) {
        super(cause);
    }

}
