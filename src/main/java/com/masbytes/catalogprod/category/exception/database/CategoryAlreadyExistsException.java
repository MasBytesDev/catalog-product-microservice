package com.masbytes.catalogprod.category.exception.database;

/**
 * Exception thrown when attempting to create a category that already exists in
 * the database.
 * Typically used to enforce uniqueness constraints on category identifiers or
 * names.
 * 
 * Extends RuntimeException.
 */

public class CategoryAlreadyExistsException extends RuntimeException {

    public CategoryAlreadyExistsException(String message) {
        super(message);
    }

    public CategoryAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryAlreadyExistsException(Throwable cause) {
        super(cause);
    }

}
