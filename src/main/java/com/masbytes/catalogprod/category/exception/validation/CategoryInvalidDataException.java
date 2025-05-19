package com.masbytes.catalogprod.category.exception.validation;

/**
 * Exception thrown when the provided data for a category is invalid or fails
 * business validation rules.
 * Common causes include null values, invalid formats, or constraint violations.
 * 
 * Extends RuntimeException.
 */

public class CategoryInvalidDataException extends RuntimeException {

    public CategoryInvalidDataException(String message) {
        super(message);
    }

    public CategoryInvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryInvalidDataException(Throwable cause) {
        super(cause);
    }

}
