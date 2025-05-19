package com.masbytes.catalogprod.category.exception.database;

/**
 * Exception thrown when a requested category cannot be found in the database.
 * This may occur during retrieval, update, or delete operations.
 * 
 * Extends RuntimeException.
 */

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryNotFoundException(Throwable cause) {
        super(cause);
    }

}
